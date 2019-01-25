import ShibaInlineComponent from "./ShibaInlineComponent";

export default class ShibaApp {
    private components: Array<{ name: string, component: ShibaInlineComponent }> = [];
    public addComponent<T extends ShibaInlineComponent>(name: string, component: T) {
        this.components.push({
            component,
            name,
        });
    }
}
