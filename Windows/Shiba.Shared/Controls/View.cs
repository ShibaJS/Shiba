using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using Shiba.Common;
using Shiba.Controls;

[assembly: ExportView("view", typeof(View))]

namespace Shiba.Controls
{
    [AttributeUsage(AttributeTargets.Assembly, AllowMultiple = true)]
    public sealed class ExportViewAttribute : Attribute
    {
        public ExportViewAttribute(string viewName, Type viewType)
        {
            ViewName = viewName;
            ViewType = viewType;
        }

        public string ViewName { get; }
        public Type ViewType { get; }
    }

    public class SbEvent
    {
        public SbEvent(string target)
        {
            Target = target;
        }

        public string Target { get; }
    }

    public enum Gravity
    {
        Start,
        End,
        Top,
        Bottom
    }

    public struct Percent : IEquatable<Percent>, IConvertible
    {
        public Percent(float value)
        {
            Value = value;
        }

        public float Value { get; }

        public bool Equals(Percent other)
        {
            return Value.Equals(other.Value);
        }

        public override bool Equals(object obj)
        {
            if (ReferenceEquals(null, obj)) return false;
            return obj is Percent percent && Equals(percent);
        }

        public override int GetHashCode()
        {
            return Value.GetHashCode();
        }

        public TypeCode GetTypeCode()
        {
            return TypeCode.Single;
        }

        public bool ToBoolean(IFormatProvider provider)
        {
            throw new InvalidCastException();
        }

        public byte ToByte(IFormatProvider provider)
        {
            throw new InvalidCastException();
        }

        public char ToChar(IFormatProvider provider)
        {
            throw new InvalidCastException();
        }

        public DateTime ToDateTime(IFormatProvider provider)
        {
            throw new InvalidCastException();
        }

        public decimal ToDecimal(IFormatProvider provider)
        {
            return Convert.ToDecimal(Value / 100f);
        }

        public double ToDouble(IFormatProvider provider)
        {
            return Value / 100f;
        }

        public short ToInt16(IFormatProvider provider)
        {
            return Convert.ToInt16(Value / 100f);
        }

        public int ToInt32(IFormatProvider provider)
        {
            return Convert.ToInt32(Value / 100f);
        }

        public long ToInt64(IFormatProvider provider)
        {
            return Convert.ToInt64(Value / 100f);
        }

        public sbyte ToSByte(IFormatProvider provider)
        {
            return Convert.ToSByte(Value / 100f);
        }

        public float ToSingle(IFormatProvider provider)
        {
            return Value / 100f;
        }

        public string ToString(IFormatProvider provider)
        {
            return Convert.ToString(Value / 100f, CultureInfo.InvariantCulture);
        }

        public object ToType(Type conversionType, IFormatProvider provider)
        {
            throw new InvalidCastException();
        }

        public ushort ToUInt16(IFormatProvider provider)
        {
            return Convert.ToUInt16(Value / 100f);
        }

        public uint ToUInt32(IFormatProvider provider)
        {
            return Convert.ToUInt32(Value / 100f);
        }

        public ulong ToUInt64(IFormatProvider provider)
        {
            return Convert.ToUInt64(Value / 100f);
        }
    }

    public struct Thickness : IEquatable<Thickness>
    {
        internal Thickness(string value)
        {
            var values = value.Split(',').Select(item => item.Trim()).ToArray();
            switch (values.Length)
            {
                case 1:
                {
                    Left = float.TryParse(values.ElementAtOrDefault(0), out var left) ? left : 0F;
                    Top = float.TryParse(values.ElementAtOrDefault(0), out var top) ? top : 0F;
                    Right = float.TryParse(values.ElementAtOrDefault(0), out var right) ? right : 0F;
                    Bottom = float.TryParse(values.ElementAtOrDefault(0), out var bottom) ? bottom : 0F;
                }
                    break;
                case 2:
                {
                    Left = float.TryParse(values.ElementAtOrDefault(0), out var left) ? left : 0F;
                    Top = float.TryParse(values.ElementAtOrDefault(1), out var top) ? top : 0F;
                    Right = float.TryParse(values.ElementAtOrDefault(0), out var right) ? right : 0F;
                    Bottom = float.TryParse(values.ElementAtOrDefault(1), out var bottom) ? bottom : 0F;
                }
                    break;
                case 4:
                {
                    Left = float.TryParse(values.ElementAtOrDefault(0), out var left) ? left : 0F;
                    Top = float.TryParse(values.ElementAtOrDefault(1), out var top) ? top : 0F;
                    Right = float.TryParse(values.ElementAtOrDefault(2), out var right) ? right : 0F;
                    Bottom = float.TryParse(values.ElementAtOrDefault(3), out var bottom) ? bottom : 0F;
                }
                    break;
                default:
                    Left = 0F;
                    Top = 0F;
                    Right = 0F;
                    Bottom = 0F;
                    break;
            }
        }

        public Thickness(float top, float left, float right, float bottom)
        {
            Top = top;
            Left = left;
            Right = right;
            Bottom = bottom;
        }

        public float Top { get; set; }
        public float Left { get; set; }
        public float Right { get; set; }
        public float Bottom { get; set; }

        public bool Equals(Thickness other)
        {
            return Top.Equals(other.Top) && Left.Equals(other.Left) && Right.Equals(other.Right) &&
                   Bottom.Equals(other.Bottom);
        }

        public override bool Equals(object obj)
        {
            if (ReferenceEquals(null, obj)) return false;
            return obj is Thickness thickness && Equals(thickness);
        }

        public override int GetHashCode()
        {
            unchecked
            {
                var hashCode = Top.GetHashCode();
                hashCode = (hashCode * 397) ^ Left.GetHashCode();
                hashCode = (hashCode * 397) ^ Right.GetHashCode();
                hashCode = (hashCode * 397) ^ Bottom.GetHashCode();
                return hashCode;
            }
        }
    }

    public class View
    {
        public View(Dictionary<string, object> attribute)
        {
            if (attribute.TryGetValue("width", out var width)) Width = width.To<float>();

            if (attribute.TryGetValue("height", out var height)) Height = height.To<float>();

            if (attribute.TryGetValue("minWidth", out var minWidth)) MinWidth = minWidth.To<float>();

            if (attribute.TryGetValue("minHeight", out var minHeight)) MinHeight = minHeight.To<float>();

            if (attribute.TryGetValue("maxWidth", out var maxWidth)) MaxWidth = maxWidth.To<float>();

            if (attribute.TryGetValue("maxHeight", out var maxHeight)) MaxHeight = maxHeight.To<float>();

            if (attribute.TryGetValue("isVisible", out var isVisible)) IsVisible = isVisible.To<bool>();

            if (attribute.TryGetValue("gravity", out var gravity) &&
                Enum.TryParse<Gravity>(gravity.ToString(), true, out var gravityResult)) Gravity = gravityResult;

            if (attribute.TryGetValue("margin", out var margin)) Margin = margin.To<Thickness>();

            if (attribute.TryGetValue("padding", out var padding)) Padding = padding.To<Thickness>();

            if (attribute.TryGetValue("enable", out var enable)) Enable = enable.To<bool>();

            if (attribute.TryGetValue("background", out var background)) Background = background?.ToString();

            if (attribute.TryGetValue("click", out var click)) Click = new SbEvent(click?.ToString());

            if (attribute.TryGetValue("sizeChanged", out var sizeChanged))
                SizeChanged = new SbEvent(sizeChanged?.ToString());

            if (attribute.TryGetValue("alpha", out var alpha)) Alpha = alpha.To<float>();

            if (attribute.TryGetValue("name", out var name)) Name = name.To<string>();
        }

        public float Width { get; set; } = float.NaN;
        public float Height { get; set; } = float.NaN;
        public float MinWidth { get; set; } = float.NaN;
        public float MinHeight { get; set; } = float.NaN;
        public float MaxWidth { get; set; } = float.NaN;
        public float MaxHeight { get; set; } = float.NaN;
        public bool IsVisible { get; set; }
        public Gravity Gravity { get; set; } = Gravity.Start;
        public Thickness Margin { get; set; }
        public Thickness Padding { get; set; }
        public bool Enable { get; set; } = true;
        public string Background { get; set; }
        public SbEvent Click { get; set; }
        public SbEvent SizeChanged { get; set; }
        public float Alpha { get; set; } = 1f;
        public string Name { get; set; }
    }

    public class ViewGroup : View
    {
        public ViewGroup(Dictionary<string, object> attribute) : base(attribute)
        {
        }

        public List<View> Children { get; set; } = new List<View>();
    }
}