using System;
using System.Windows.Forms;

namespace MVCDemo {

    /// <summary>
    /// Controller class of the MVC architecture
    /// </summary>
    public partial class Controller : Form {

        // Data Model observed
        private Source_Code.DataModel DataModel;


        /// <summary>
        /// Initilizes component
        /// </summary>
        /// <param name="DataModel">Data Model Observed</param>
        public Controller(Source_Code.DataModel DataModel) {

            this.DataModel = DataModel;
            InitializeComponent();

        }

        /// <summary>
        /// Increments data by 1
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void incrementBy1_Click(object sender, EventArgs e) {


            DataModel.incrementData(1);

        }


        /// <summary>
        /// Increments data by 10
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void incrementBy10_Click(object sender, EventArgs e) {

            DataModel.incrementData(10);

        }



        /// <summary>
        /// Adds observer when component is loaded since the component contains a viewer
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void controller_Load(object sender, EventArgs e) {

            DataModel.addObserver(this.Display);

        }

        /// <summary>
        /// Checks each key entered in the text box if it is the Enter
        /// key and when enter is hit, it sends the data to the model
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e">Key Pressed Event</param>
        private void enterValue_KeyDown(object sender, KeyEventArgs e) {


            try {

                // Checks if the enter key was hit
                if (e.KeyCode == Keys.Enter) {
                    DataModel.incrementData(Convert.ToInt32(this.ValueTextBox.Text));
                    this.ValueTextBox.Text = "";
                }


            }
            // If NaN then throw error
            catch (Exception nfe) {

                MessageBox.Show(nfe.Message);
                this.ValueTextBox.Text = "";

            }



        }

        /// <summary>
        /// Creeates new viwers when clicked
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void createNewViewer_Click(object sender, EventArgs e) {

            ViewerWindow viewer2 = new(DataModel);
            viewer2.Show();

            OptionalViewerWindow viewer3 = new(DataModel);
            viewer3.Show();

            this.CreateNewViewerButton.Enabled = false;

        }


    }
}
