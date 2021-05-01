using System;
using System.Windows.Forms;

namespace MVCDemo {

    /// <summary>
    /// Viewer class that has the ability to unsubscribe and resubscribe from the model
    /// </summary>
    public partial class OptionalViewerWindow : Form {

        // Data Model Observed
        Source_Code.DataModel DataModel;


        /// <summary>
        /// Initilizes component
        /// </summary>
        /// <param name="DataModel">Data Model Observed</param>
        public OptionalViewerWindow(Source_Code.DataModel DataModel) {

            this.DataModel = DataModel;
            InitializeComponent();
        }

        /// <summary>
        /// When window is loaded, subscribes the display to the Model
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void optionalViewerWindow_Load(object sender, EventArgs e) {

            this.DataModel.addObserver(this.Display);
            this.Display.Text = $"The current value is: {this.DataModel.dataProperty}";
            this.SubButton.Enabled = false;

        }

        /// <summary>
        /// Subscribes the Display to the Data Model
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void sub_Click(object sender, EventArgs e) {

            this.DataModel.addObserver(this.Display);

            // Prevents double subscribing
            this.UnsubButton.Enabled = true;
            this.SubButton.Enabled = false;

        }


        /// <summary>
        /// Unsubscribes the display from the Data Model
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void unSub_Click(object sender, EventArgs e) {

            this.DataModel.removeObserver(this.Display);

            // Prevents double unsubscribing
            this.SubButton.Enabled = true;
            this.UnsubButton.Enabled = false;

        }


    }
}
