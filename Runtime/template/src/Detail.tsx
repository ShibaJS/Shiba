import Shiba, { binding } from "@shibajs/core";

registerComponent("detail",
    <stack>
        <text text={binding("text", (it) => {
            return it + "hello!";
        })}/>
    </stack>,
);
