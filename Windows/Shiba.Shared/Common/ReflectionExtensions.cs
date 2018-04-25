using System;
using System.Linq;
using System.Linq.Expressions;
using System.Reflection;

namespace Shiba.Common
{
    public static class ReflectionExtensions
    {
        public static T CreateInstance<T>(this string typeName) where T: class
        {
            return typeName.CreateInstence() as T;
        }

        public static T CreateInstance<T>(this Type type) where T : class
        {
            return type.CreateInstance() as T;
        }

        public static object CreateInstence(this string typeName)
        {
            return Type.GetType(typeName)?.CreateInstance();
        }

        public static object CreateInstance(this Type type)
        {
            var constructor = type?.GetConstructor(Type.EmptyTypes);
            if (constructor == null) return null;
            return Expression.Lambda<Func<object>>(
                Expression.New(constructor)
            ).Compile();
        }
    }

    public static class StringExtensions
    {
        public static string FirstToUpper(this string input)
        {    switch (input)
            {
                case null: throw new ArgumentNullException(nameof(input));
                case "": throw new ArgumentException($"{nameof(input)} cannot be empty", nameof(input));
                default: return input.First().ToString().ToUpper() + input.Substring(1);
            }
        }
    }
}