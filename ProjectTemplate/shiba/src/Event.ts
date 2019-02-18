export default class Event<T> {
    private listeners: Array<(sender: any, arg: T) => void> = [];
    public invoke(sender: any, arg: T) {
        this.listeners.forEach((it) => it(sender, arg));
    }
    public register(func: (sender: any, arg: T) => void) {
        this.listeners.push(func);
    }
    public unregister(func: (sender: any, arg: T) => void) {
        const index = this.listeners.indexOf(func);
        if (index !== -1) {
            this.listeners.slice(index, 1);
        }
    }
}
