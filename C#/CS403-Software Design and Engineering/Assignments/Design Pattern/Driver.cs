using System;
using System.Collections.Generic;


namespace MVCDemo {

public class Driver {


    public static void Main(string[] args) {

        Viewer viewer1 = new Viewer();

        DataModel dm1 = new DataModel(5);

        Console.WriteLine(dm1.dataProperty);

        dm1.addObserver(viewer1);

        dm1.bumpData(5);

        List<Viewer> listOfViewers = new List<Viewer>();

        for (int i = 0; i < 5; i++) {
            
            listOfViewers.Add(new Viewer());
            dm1.addObserver(listOfViewers[i]);

        }

        dm1.bumpData(12);
        
        Console.WriteLine("Enter a value");
        var usrInt = Convert.ToInt32(Console.ReadLine());
        dm1.bumpData(usrInt);


        for (int i = listOfViewers.Count - 1; i >= 1 ; i--) {
            
            dm1.removeObserver(listOfViewers[i]);

        }

        dm1.bumpData(2);

        dm1.removeAllObservers();

        Console.WriteLine("Here its incrementing again");
        dm1.bumpData(2);

    }


}

}
