import { ShibaComponent } from "@shibajs/core";
class Index extends ShibaComponent {
    view(): string {
        throw new Error("Method not implemented.");
    }
}
registerComponent("", () => new Index());