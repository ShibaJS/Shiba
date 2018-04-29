using System;
using System.Globalization;

namespace Shiba.Controls
{
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
}