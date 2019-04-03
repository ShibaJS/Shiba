import ShibaComponent from "./ShibaComponent";
declare global {
    function registerComponent(name: string, creator: () => ShibaComponent): void;
}
export { ShibaComponent, };
