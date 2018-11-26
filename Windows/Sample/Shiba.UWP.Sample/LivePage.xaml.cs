using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net.Http;
using System.Runtime.InteropServices.WindowsRuntime;
using System.Threading.Tasks;
using Windows.Foundation;
using Windows.Foundation.Collections;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Controls.Primitives;
using Windows.UI.Xaml.Data;
using Windows.UI.Xaml.Input;
using Windows.UI.Xaml.Media;
using Windows.UI.Xaml.Navigation;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;

// The Blank Page item template is documented at https://go.microsoft.com/fwlink/?LinkId=234238

namespace Shiba.UWP.Sample
{
    /// <summary>
    /// An empty page that can be used on its own or navigated to within a Frame.
    /// </summary>
    public sealed partial class LivePage : Page
    {
        public Streaming Streaming { get; set; } = new Streaming();

        public LivePage()
        {
            this.InitializeComponent();
            Streaming.OnLayoutChanged = layout => ShibaHost.Layout = layout;
            Streaming.OnDataChanged = token => ShibaHost.DataContext = token;
        }
    }


    public class Streaming
    {
        public Action<JToken> OnDataChanged { get; set; }
        public Action<string> OnLayoutChanged { get; set; }

        private HttpClient client;

        public string Host { get; set; } = "http://localhost:5000/streaming";

        public async Task Start()
        {
            client = new HttpClient();

            var stream = await client.GetStreamAsync(Host);

            var reader = new StreamReader(stream);

            string eventName = null;
            string data = null;

            while (client != null)
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
            if (client != null)
            {
                client.Dispose();
                client = null;
            }
        }

    }
}
