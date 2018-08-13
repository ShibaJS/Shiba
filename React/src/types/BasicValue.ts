
enum ShibaValueType {
    String,
    Token,
    Number,
    Null,
    Boolean,
}

class BasicValue {
    public readonly typeCode: ShibaValueType;
    public readonly value: any;

    constructor(typeCode: ShibaValueType, value: any) {
        this.typeCode = typeCode;
        this.value = value;
    }
}

export {ShibaValueType, BasicValue}