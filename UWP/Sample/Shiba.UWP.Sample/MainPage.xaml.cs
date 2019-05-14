using System;
using System.ComponentModel;
using System.IO;
using System.Linq;
using System.Net.Http;
using System.Runtime.CompilerServices;
using System.Threading.Tasks;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Navigation;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;

namespace Shiba.UWP.Sample
{

    public sealed partial class MainPage : Page
    {
        public MainPage()
        {
            InitializeComponent();
            NavigationCacheMode = NavigationCacheMode.Required;
        }

        protected override async void OnNavigatedTo(NavigationEventArgs e)
        {
            base.OnNavigatedTo(e);
            var fname = @"Assets\bundle.js";
            var InstallationFolder = Windows.ApplicationModel.Package.Current.InstalledLocation;
            var file = await InstallationFolder.GetFileAsync(fname);
            var contents = await File.ReadAllTextAsync(file.Path);
            ShibaApp.Instance.Configuration.ScriptRuntime.Execute(contents);
            ShibaHost.DataContext = new
            {
                text = "test!"
            };
            ShibaHost.Component = "index";
        }
    }

    
    public class Streaming
    {
        public Action<JToken> OnDataChanged { get; set; }
        public Action<string> OnLayoutChanged { get; set; }

        private HttpClient _client;

        public string Host { get; set; } = "http://localhost:5000/streaming";

        public async Task Start()
        {
            _client = new HttpClient();

            var stream = await _client.GetStreamAsync(Host);

            var reader = new StreamReader(stream);

            string eventName = null;
            string data = null;

            while (_client != null)
            {
                var line = await reader.ReadLineAsync();


                if (string.IsNullOrEmpty(line) || line.StartsWith(":"))
                {
                    eventName = data = null;
                    continue;
                }

                if (line.StartsWith("event: "))
                {
                    eventName = line.Substring("event: ".Length).Trim();
                }
                else if (line.StartsWith("data: "))
                {
                    data = line.Substring("data: ".Length).Trim('"');

                    switch (eventName)
                    {
                        case "data_changed":
                            OnDataChanged?.Invoke(JsonConvert.DeserializeObject<JToken>(data));
                            break;
                        case "layout_changed":
                            OnLayoutChanged?.Invoke(data);
                            break;
                    }
                }
            }
            this.Stop();
        }

        public void Stop()
        {
            if (_client != null)
            {
                _client.Dispose();
                _client = null;
            }
        }

    }
}