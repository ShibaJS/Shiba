import Shiba, { binding } from "@shibajs/core";
import "./detail";

registerComponent("index",
    <grid column={2}>
        <text grid={{ column: 1 }} text="index" />
        <detail grid={{ column: 0 }} />
    </grid>,
);
