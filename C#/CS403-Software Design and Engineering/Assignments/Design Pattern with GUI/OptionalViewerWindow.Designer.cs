
namespace MVCDemo {
    partial class OptionalViewerWindow {
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
            this.UnsubButton = new System.Windows.Forms.Button();
            this.SubButton = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // Display
            // 
            this.Display.Font = new System.Drawing.Font("Tahoma", 20.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.Display.Location = new System.Drawing.Point(140, 9);
            this.Display.Name = "Display";
            this.Display.Size = new System.Drawing.Size(465, 287);
            this.Display.TabIndex = 0;
            this.Display.Text = "The current value is: ";
            this.Display.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // UnsubButton
            // 
            this.UnsubButton.Font = new System.Drawing.Font("Tahoma", 20.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.UnsubButton.Location = new System.Drawing.Point(561, 299);
            this.UnsubButton.Name = "UnsubButton";
            this.UnsubButton.Size = new System.Drawing.Size(238, 151);
            this.UnsubButton.TabIndex = 1;
            this.UnsubButton.Text = "Unsubscribe";
            this.UnsubButton.UseVisualStyleBackColor = true;
            this.UnsubButton.Click += new System.EventHandler(this.unSub_Click);
            // 
            // SubButton
            // 
            this.SubButton.Font = new System.Drawing.Font("Tahoma", 20.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.SubButton.Location = new System.Drawing.Point(0, 299);
            this.SubButton.Name = "SubButton";
            this.SubButton.Size = new System.Drawing.Size(238, 151);
            this.SubButton.TabIndex = 2;
            this.SubButton.Text = "Subscribe";
            this.SubButton.UseVisualStyleBackColor = true;
            this.SubButton.Click += new System.EventHandler(this.sub_Click);
            // 
            // OptionalViewerWindow
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.SubButton);
            this.Controls.Add(this.UnsubButton);
            this.Controls.Add(this.Display);
            this.Name = "OptionalViewerWindow";
            this.Text = "Optional Viewer Window";
            this.Load += new System.EventHandler(this.optionalViewerWindow_Load);
            this.ResumeLayout(false);

        }

        #endregion

        private Source_Code.Viewer Display;
        private System.Windows.Forms.Button UnsubButton;
        private System.Windows.Forms.Button SubButton;
    }
}