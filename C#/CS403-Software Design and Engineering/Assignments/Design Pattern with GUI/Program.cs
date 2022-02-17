using System;
using System.Windows.Forms;

namespace MVCDemo {
    static class Program {
        /// <summary>
        ///  <author>Ante Zovko</author>
        ///  <version>April 30th, 2021</version>
        /// 
        ///  The main entry point for the application.
        ///  
        /// </summary>
        [STAThread]
        static void Main() {
            Application.SetHighDpiMode(HighDpiMode.SystemAware);
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            Application.Run(new Controller(new Source_Code.DataModel()));



        }
    }
}
