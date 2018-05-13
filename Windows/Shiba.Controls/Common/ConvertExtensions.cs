using System;
using System.Linq;
using System.Reflection;

namespace Shiba.Controls.Common
{
    internal static class ConvertExtensions
    {
        public static T To<T>(this object value)
        {
            if (typeof(T).GetTypeInfo().IsEnum)
            {
                return (T) Enum.Parse(typeof(T), value?.ToString() ?? throw new ArgumentNullException());
            }

            switch (value)
            {
                case T resultValue:
                    return resultValue;
                case IConvertible convertible:
                    return (T) Convert.ChangeType(convertible, typeof(T));
                default:
                    throw new InvalidCastException($"Can not cast ${value?.GetType()} to {typeof(T)}");
            }
        }
    }

    internal static class StringExtensions
    {
        public static string FirstToUpper(this string input)
        {
            switch (input)
            {
                case null: throw new ArgumentNullException(nameof(input));
                case "": throw new ArgumentException($"{nameof(input)} cannot be empty", nameof(input));
                default: return input.FirstOrDefault().ToString().ToUpper() + input.Substring(1);
            }
        }
    }
}