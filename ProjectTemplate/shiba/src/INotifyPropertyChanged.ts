import Event from "./Event";

export default interface INotifyPropertyChanged {
    propertyChanged: Event<string>;
}
