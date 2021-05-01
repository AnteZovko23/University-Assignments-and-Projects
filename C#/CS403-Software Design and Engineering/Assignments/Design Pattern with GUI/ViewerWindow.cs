using System;
using System.Windows.Forms;

namespace MVCDemo {

    /// <summary>
    /// Creates a Viewer in the MVC architecture
    /// 
    /// </summary>
    public partial class ViewerWindow : Form {

        // Data Model to be observed
        private Source_Code.DataModel DataModel;


        /// <summary>
        /// Initializes Component
        /// </summary>
        /// <param name="DataModel">Data Model Observed</param>
        public ViewerWindow(Source_Code.DataModel DataModel) {

            this.DataModel = DataModel;
            InitializeComponent();
        }

        /// <summary>
        /// When the widnow loads, the display is added as an observer of the Data Model
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void viewerWindow_Load(object sender, EventArgs e) {

            DataModel.addObserver(this.Display);
            this.Display.Text = $"The current value is: {this.DataModel.dataProperty}";

        }


    }
}
