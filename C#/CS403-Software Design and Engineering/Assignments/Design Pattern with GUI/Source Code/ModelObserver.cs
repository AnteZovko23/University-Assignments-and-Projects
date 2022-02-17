namespace MVCDemo.Source_Code {

    /// <summary>
    /// The observer interface or viewers have to implement
    /// </summary>
    public interface IModelObserver {

        /// <summary>
        /// Updates the viewer when model changes
        /// </summary>
        /// <param name="DataModelItem"> The DataModel being observed</param>
        void update(DataModel DataModelItem);

    }
}
