﻿using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using Shiba.Controls;
#if WINDOWS_UWP
using Windows.UI;
using NativeThickness = Windows.UI.Xaml.Thickness;
#elif WPF
using System.Windows;
using System.Windows.Media;
using NativeThickness = System.Windows.Thickness;
using System.Drawing;
#elif FORMS 
using Xamarin.Forms;
using NativeThickness = Xamarin.Forms.Thickness;
#endif

namespace Shiba
{
    internal static class ConvertExtensions
    {

        public static bool TryChangeType(this object value, Type type, out object result)
        {
            if (value == null)
            {
                result = null;
                return false;
            }

            if (type == null)
            {
                result = value;
                return false;
            }

            if (value.GetType() == type)
            {
                result = value;
                return true;
            }
            
            if (value.CanChangeType(type))
            {
                result = Convert.ChangeType(value, type);
                return true;
            }

            result = value;
            return false;

        }

        public static bool CanChangeType(this object value, Type conversionType)
        {
            if (conversionType == null)
            {
                return false;
            }

            if (value == null)
            {              
                return false;
            }

            return value is IConvertible;
        }
        
        public static Color ToNativeColor(this string colorString)
        {
            if (string.IsNullOrEmpty(colorString))
            {
                throw new ArgumentException(nameof(colorString));
            }

            if (colorString[0] == '#')
            {
                switch (colorString.Length)
                {
                    case 9:
                    {
                        var cuint = Convert.ToUInt32(colorString.Substring(1), 16);
                        var a = (byte)(cuint >> 24);
                        var r = (byte)((cuint >> 16) & 0xff);
                        var g = (byte)((cuint >> 8) & 0xff);
                        var b = (byte)(cuint & 0xff);
#if FORMS
                        return Color.FromRgba(r, g, b, a);
#else
                        return Color.FromArgb(a, r, g, b);
#endif
                    }

                    case 7:
                    {
                        var cuint = Convert.ToUInt32(colorString.Substring(1), 16);
                        var r = (byte)((cuint >> 16) & 0xff);
                        var g = (byte)((cuint >> 8) & 0xff);
                        var b = (byte)(cuint & 0xff);
#if FORMS
                        return Color.FromRgb(r, g, b);
#else
                        return Color.FromArgb(255, r, g, b);
#endif
                    }

                    case 5:
                    {
                        var cuint = Convert.ToUInt16(colorString.Substring(1), 16);
                        var a = (byte)(cuint >> 12);
                        var r = (byte)((cuint >> 8) & 0xf);
                        var g = (byte)((cuint >> 4) & 0xf);
                        var b = (byte)(cuint & 0xf);
                        a = (byte)(a << 4 | a);
                        r = (byte)(r << 4 | r);
                        g = (byte)(g << 4 | g);
                        b = (byte)(b << 4 | b);
#if FORMS
                        return Color.FromRgba(r, g, b, a);
#else               
                        return Color.FromArgb(a, r, g, b);
#endif
                    }

                    case 4:
                    {
                        var cuint = Convert.ToUInt16(colorString.Substring(1), 16);
                        var r = (byte)((cuint >> 8) & 0xf);
                        var g = (byte)((cuint >> 4) & 0xf);
                        var b = (byte)(cuint & 0xf);
                        r = (byte)(r << 4 | r);
                        g = (byte)(g << 4 | g);
                        b = (byte)(b << 4 | b);
#if FORMS
                        return Color.FromRgb(r, g, b);
#else
                        return Color.FromArgb(255, r, g, b);
#endif
                    }

                    default:
                        throw new FormatException(
                            $"The {colorString} string passed in the colorString argument is not a recognized Color format.");
                }
            }

            if (colorString.Length > 3 && colorString[0] == 's' && colorString[1] == 'c' && colorString[2] == '#')
            {
                var values = colorString.Split(',');

                switch (values.Length)
                {
                    case 4:
                    {
                        var scA = double.Parse(values[0].Substring(3));
                        var scR = double.Parse(values[1]);
                        var scG = double.Parse(values[2]);
                        var scB = double.Parse(values[3]);
#if FORMS
                        return Color.FromRgba((byte)(scR * 255), (byte)(scG * 255), (byte)(scB * 255), (byte)(scA * 255));
#else
                        return Color.FromArgb((byte)(scA * 255), (byte)(scR * 255), (byte)(scG * 255), (byte)(scB * 255));
#endif
                    }
                    case 3:
                    {
                        var scR = double.Parse(values[0].Substring(3));
                        var scG = double.Parse(values[1]);
                        var scB = double.Parse(values[2]);
#if FORMS
                        return Color.FromRgb((byte)(scR * 255), (byte)(scG * 255), (byte)(scB * 255));
#else
                        return Color.FromArgb(255, (byte)(scR * 255), (byte)(scG * 255), (byte)(scB * 255));
#endif
                    }
                    default:
                        throw new FormatException(
                            $"The {colorString} string passed in the colorString argument is not a recognized Color format (sc#[scA,]scR,scG,scB).");
                }

            }

#if FORMS
            var prop = typeof(Color).GetTypeInfo().DeclaredProperties.FirstOrDefault(it =>
                string.Equals(it.Name, colorString, StringComparison.OrdinalIgnoreCase));
#else
            var prop = typeof(Colors).GetTypeInfo().DeclaredProperties.FirstOrDefault(it =>
                string.Equals(it.Name, colorString, StringComparison.OrdinalIgnoreCase));
#endif

            if (prop != null)
            {
                return (Color)prop.GetValue(null);
            }

            throw new FormatException(
                $"The {colorString} string passed in the colorString argument is not a recognized Color.");
        }
    }
}