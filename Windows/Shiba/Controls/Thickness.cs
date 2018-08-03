using System;
using System.Linq;

namespace Shiba.Controls
{
//    public struct Thickness : IEquatable<Thickness>
//    {
//        public Thickness(string value)
//        {
//            var values = value.Trim('[', ']').Split(',').Select(item => item.Trim()).ToArray();
//            switch (values.Length)
//            {
//                case 1:
//                {
//                    Left = float.TryParse(values.ElementAtOrDefault(0), out var left) ? left : 0F;
//                    Top = float.TryParse(values.ElementAtOrDefault(0), out var top) ? top : 0F;
//                    Right = float.TryParse(values.ElementAtOrDefault(0), out var right) ? right : 0F;
//                    Bottom = float.TryParse(values.ElementAtOrDefault(0), out var bottom) ? bottom : 0F;
//                }
//                    break;
//                case 2:
//                {
//                    Left = float.TryParse(values.ElementAtOrDefault(0), out var left) ? left : 0F;
//                    Top = float.TryParse(values.ElementAtOrDefault(1), out var top) ? top : 0F;
//                    Right = float.TryParse(values.ElementAtOrDefault(0), out var right) ? right : 0F;
//                    Bottom = float.TryParse(values.ElementAtOrDefault(1), out var bottom) ? bottom : 0F;
//                }
//                    break;
//                case 4:
//                {
//                    Left = float.TryParse(values.ElementAtOrDefault(0), out var left) ? left : 0F;
//                    Top = float.TryParse(values.ElementAtOrDefault(1), out var top) ? top : 0F;
//                    Right = float.TryParse(values.ElementAtOrDefault(2), out var right) ? right : 0F;
//                    Bottom = float.TryParse(values.ElementAtOrDefault(3), out var bottom) ? bottom : 0F;
//                }
//                    break;
//                default:
//                    Left = 0F;
//                    Top = 0F;
//                    Right = 0F;
//                    Bottom = 0F;
//                    break;
//            }
//        }
//
//        public Thickness(float top, float left, float right, float bottom)
//        {
//            Top = top;
//            Left = left;
//            Right = right;
//            Bottom = bottom;
//        }
//
//        public float Top { get; set; }
//        public float Left { get; set; }
//        public float Right { get; set; }
//        public float Bottom { get; set; }
//
//        public bool Equals(Thickness other)
//        {
//            return Top.Equals(other.Top) && Left.Equals(other.Left) && Right.Equals(other.Right) &&
//                   Bottom.Equals(other.Bottom);
//        }
//
//        public override bool Equals(object obj)
//        {
//            if (ReferenceEquals(null, obj))
//            {
//                return false;
//            }
//
//            return obj is Thickness thickness && Equals(thickness);
//        }
//
//        public override int GetHashCode()
//        {
//            unchecked
//            {
//                var hashCode = Top.GetHashCode();
//                hashCode = (hashCode * 397) ^ Left.GetHashCode();
//                hashCode = (hashCode * 397) ^ Right.GetHashCode();
//                hashCode = (hashCode * 397) ^ Bottom.GetHashCode();
//                return hashCode;
//            }
//        }
//    }
}