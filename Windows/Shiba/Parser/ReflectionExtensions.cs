using System;
using System.Linq;
using System.Linq.Expressions;
using System.Reflection;

namespace Shiba.Parser
{
    internal static class ReflectionExtensions
    {
        public static T CreateInstance<T>(this string typeName, params object[] param) where T : class
        {
            return typeName.CreateInstence(param) as T;
        }

        public static T CreateInstance<T>(this Type type, params object[] param) where T : class
        {
            return type.CreateInstance(param) as T;
        }

        public static object CreateInstence(this string typeName, params object[] param)
        {
            return Type.GetType(typeName)?.CreateInstance(param);
        }

        public static object CreateInstance(this Type type, params object[] param)
        {
            ConstructorInfo constructor;
            if (param != null)
            {
                constructor = type?.GetConstructors()?
                    .FirstOrDefault(c =>
                        !c.GetParameters()
                            .Where((t, i) => t.ParameterType != param.ElementAtOrDefault(i)?.GetType())
                            .Any());
            }
            else
            {
                constructor = type?.GetConstructor(Type.EmptyTypes);
            }

            if (constructor == null)
            {
                return null;
            }

            if (param != null && param.Length > 0)
            {
                return constructor.Invoke(param);
            }

            return Expression.Lambda<Func<object>>(
                Expression.New(constructor)
            ).Compile().Invoke();
        }
    }
}