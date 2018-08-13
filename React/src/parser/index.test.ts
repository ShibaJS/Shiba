import ShibaParserWrapper from "./ShibaParserWrapper";

it('should parse layout', () => {
    const layout = ShibaParserWrapper.parse("\n" +
        "                stack {\n" +
        "                text -> awesome(reverse($bind UWPText))\n" +
        "                text {\n" +
        "                \n" +
        "                color = \"#ffff00\"\n" +
        "                text = \"fdsafdsaf\"\n" +
        "                size = 20\n" +
        "                }\n" +
        "                input -> $bind UWPText\n" +
        "                }");
    expect(layout !== null);
    expect(layout !== undefined);
});