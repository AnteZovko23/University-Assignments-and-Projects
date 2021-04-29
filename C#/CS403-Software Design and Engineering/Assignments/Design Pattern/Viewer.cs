using System;
namespace MVCDemo {

    public class Viewer : ModelObserver {


        public void update(DataModel DataModelItem){

            Console.WriteLine($"This Viewer got notified.\nNew value is {DataModelItem.dataProperty}\n");

        }
        

    }

}