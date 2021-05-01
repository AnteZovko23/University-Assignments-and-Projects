
namespace MVCDemo {
    partial class ViewerWindow {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing) {
            if (disposing && (components != null)) {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent() {
            this.Display = new MVCDemo.Source_Code.Viewer();
            this.SuspendLayout();
            // 
            // Display
            // 
            this.Display.Font = new System.Drawing.Font("Tahoma", 20.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.Display.Location = new System.Drawing.Point(77, 39);
            this.Display.Name = "Display";
            this.Display.Size = new System.Drawing.Size(594, 336);
            this.Display.TabIndex = 0;
            this.Display.Text = "The current value is: ";
            this.Display.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // ViewerWindow
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.Display);
            this.Name = "ViewerWindow";
            this.Text = "ViewerWindow";
            this.Load += new System.EventHandler(this.viewerWindow_Load);
            this.ResumeLayout(false);

        }

        #endregion

        private Source_Code.Viewer Display;
    }
}