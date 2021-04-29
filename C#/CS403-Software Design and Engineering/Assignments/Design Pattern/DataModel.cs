using System;
using System.Collections.Generic;

namespace MVCDemo {

public class DataModel {


    private int data;
    private List<ModelObserver> ModelObserverList;

    public DataModel() {

        this.data = 0;
        this.ModelObserverList = new List<ModelObserver>();

    }


    public DataModel(int data) {

        this.data = data;
        this.ModelObserverList = new List<ModelObserver>();

    }


    public int dataProperty {

        get {return this.data;}
        set {this.data = value;
             notifyObservers();}
    }


    public void bumpData(int value) {

        this.data += value;
        notifyObservers();

    }

    public void addObserver(ModelObserver ModelObserverItem) {

        this.ModelObserverList.Add(ModelObserverItem);

    }
    public void notifyObservers() {

        foreach (ModelObserver ModelObserverItem in this.ModelObserverList) {
            
            ModelObserverItem.update(this);

        }

   
    }


 public void removeObserver(ModelObserver ModelObserverItem) {

        if(this.ModelObserverList != null) {

            this.ModelObserverList.Remove(ModelObserverItem);

        }

    }

    public void removeAllObservers(){

         if(this.ModelObserverList != null) {

            this.ModelObserverList.Clear();

        }

    }



}

}
