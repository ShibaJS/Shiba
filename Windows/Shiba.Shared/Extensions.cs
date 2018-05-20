using System;
using Shiba.Controls;

namespace Shiba
{
    internal static class TokenExtensions
    {
        public static string GetTokenValue(this IToken value)
        {
            if (value is Token token)
            {
                return token.Value;
            }
            throw new ArgumentOutOfRangeException($"Line {value.Line}, colunm {value.Column} should be token");
        }
    }
}