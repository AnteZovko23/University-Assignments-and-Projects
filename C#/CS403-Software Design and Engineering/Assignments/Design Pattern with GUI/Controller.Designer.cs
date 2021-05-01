
using System;

namespace MVCDemo
{
    partial class Controller
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent() {
            this.IncrementBy1Button = new System.Windows.Forms.Button();
            this.IncrementBy10Button = new System.Windows.Forms.Button();
            this.ValueTextBox = new System.Windows.Forms.TextBox();
            this.CreateNewViewerButton = new System.Windows.Forms.Button();
            this.Display = new MVCDemo.Source_Code.Viewer();
            this.SuspendLayout();
            // 
            // IncrementBy1Button
            // 
            this.IncrementBy1Button.Font = new System.Drawing.Font("Tahoma", 20.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.IncrementBy1Button.Location = new System.Drawing.Point(0, 230);
            this.IncrementBy1Button.Name = "IncrementBy1Button";
            this.IncrementBy1Button.Size = new System.Drawing.Size(205, 220);
            this.IncrementBy1Button.TabIndex = 0;
            this.IncrementBy1Button.Text = "+1";
            this.IncrementBy1Button.UseVisualStyleBackColor = true;
            this.IncrementBy1Button.Click += new System.EventHandler(this.incrementBy1_Click);
            // 
            // IncrementBy10Button
            // 
            this.IncrementBy10Button.Font = new System.Drawing.Font("Tahoma", 20.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.IncrementBy10Button.Location = new System.Drawing.Point(441, 230);
            this.IncrementBy10Button.Name = "IncrementBy10Button";
            this.IncrementBy10Button.Size = new System.Drawing.Size(204, 220);
            this.IncrementBy10Button.TabIndex = 1;
            this.IncrementBy10Button.Text = "+10";
            this.IncrementBy10Button.UseVisualStyleBackColor = true;
            this.IncrementBy10Button.Click += new System.EventHandler(this.incrementBy10_Click);
            // 
            // ValueTextBox
            // 
            this.ValueTextBox.Font = new System.Drawing.Font("Tahoma", 20.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.ValueTextBox.Location = new System.Drawing.Point(211, 248);
            this.ValueTextBox.Name = "ValueTextBox";
            this.ValueTextBox.PlaceholderText = "Enter a value...";
            this.ValueTextBox.Size = new System.Drawing.Size(224, 40);
            this.ValueTextBox.TabIndex = 2;
            this.ValueTextBox.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.ValueTextBox.KeyDown += new System.Windows.Forms.KeyEventHandler(this.enterValue_KeyDown);
            // 
            // CreateNewViewerButton
            // 
            this.CreateNewViewerButton.Font = new System.Drawing.Font("Tahoma", 20.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.CreateNewViewerButton.Location = new System.Drawing.Point(211, 313);
            this.CreateNewViewerButton.Name = "CreateNewViewerButton";
            this.CreateNewViewerButton.Size = new System.Drawing.Size(224, 137);
            this.CreateNewViewerButton.TabIndex = 3;
            this.CreateNewViewerButton.Text = "Create new viewer";
            this.CreateNewViewerButton.UseVisualStyleBackColor = true;
            this.CreateNewViewerButton.Click += new System.EventHandler(this.createNewViewer_Click);
            // 
            // Display
            // 
            this.Display.Font = new System.Drawing.Font("Tahoma", 20.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.Display.Location = new System.Drawing.Point(0, 0);
            this.Display.Name = "Display";
            this.Display.Size = new System.Drawing.Size(645, 218);
            this.Display.TabIndex = 4;
            this.Display.Text = "The current value is: 0";
            this.Display.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // Controller
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.SystemColors.ControlLightLight;
            this.ClientSize = new System.Drawing.Size(643, 450);
            this.Controls.Add(this.Display);
            this.Controls.Add(this.CreateNewViewerButton);
            this.Controls.Add(this.ValueTextBox);
            this.Controls.Add(this.IncrementBy10Button);
            this.Controls.Add(this.IncrementBy1Button);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.Fixed3D;
            this.Name = "Controller";
            this.Text = "MVC Demo";
            this.Load += new System.EventHandler(this.controller_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }
        #endregion

        private System.Windows.Forms.Button IncrementBy1Button;
        private System.Windows.Forms.Button IncrementBy10Button;
        private System.Windows.Forms.TextBox ValueTextBox;
        private System.Windows.Forms.Button CreateNewViewerButton;
        private Source_Code.Viewer Display;
    }
}

