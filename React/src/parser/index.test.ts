import ShibaParserWrapper from "./ShibaParserWrapper";

it('should parse layout', () => {
    const layout = ShibaParserWrapper.parse("stack { text -> awesome(reverse($bind UWPText)) text { color = \"#ffff00\" text = \"fdsafdsaf\" size = 20 } input -> $bind UWPText }");
    expect(layout !== null);
    expect(layout !== undefined);
});