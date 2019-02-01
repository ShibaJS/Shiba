import ShibaInlineComponent from "./ShibaInlineComponent";

export default class ShibaApp {
    private components: Array<{ name: string, component: ShibaInlineComponent }> = [];
    public addComponent<T extends ShibaInlineComponent>(name: string, component: T) {
        this.components.push({
            component,
            name,
        });
    }
    public getComponent(name: string): ShibaInlineComponent {
        return this.components.filter((it) => it.name === name)[0].component;
    }
}
