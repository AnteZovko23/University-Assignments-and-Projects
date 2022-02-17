namespace MVCDemo.Source_Code {
    /// <summary>
    /// Viewer inherits from System.Windows.Forms.Label and implements the IModelObserver interface
    /// which allows the component to subscribe to the Data Model while also having the functionality of a display
    /// to show the data
    /// </summary>
    public partial class Viewer : System.Windows.Forms.Label, IModelObserver {

        /// <summary>
        /// Initilizes component
        /// </summary>
        public Viewer() {
            InitializeComponent();
        }

        /// <summary>
        /// Updates the current value of the Data Model when called
        /// </summary>
        /// <param name="DataModelItem">The Data Model being observed</param>
        public void update(DataModel DataModelItem) {
            this.Text = $"The current value is: {DataModelItem.dataProperty}";
        }
    }
}
