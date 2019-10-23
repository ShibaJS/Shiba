
// Global declaration
// @ts-ignore
declare global {
    // @ts-ignore
    function registerComponent(name: string, view: IView): void;
    // @ts-ignore
    // function runShibaApp(view: IView): void;
    namespace JSX {
        // tslint:disable-next-line:interface-name
        interface IntrinsicElements {
            [name: string]: any;
        }
    }
}
