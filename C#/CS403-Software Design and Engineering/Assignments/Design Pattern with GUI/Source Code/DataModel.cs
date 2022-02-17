using System.Collections.Generic;

namespace MVCDemo.Source_Code {
    /// <summary>
    /// A simple data model class used for MVC demo
    /// </summary>
    public class DataModel {

        
        // The data that is being manipulated
        private int data;
        // The list of observers subscribed to the model
        private List<IModelObserver> ModelObserverList;

        /// <summary>
        /// Default constructor
        /// Sets data to 0
        /// </summary>
        public DataModel() {

            this.data = 0;
            this.ModelObserverList = new List<IModelObserver>();

        }


        /// <summary>
        /// Constructor for DataModel
        /// </summary>
        /// <param name="data">The given data</param>
        public DataModel(int data) {

            this.data = data;
            this.ModelObserverList = new List<IModelObserver>();

        }

        /// <summary>
        /// Data Property which contains the getter and setter
        /// The setter notifies the observers if called
        /// </summary>
        public int dataProperty {

            get { return this.data; }
            set {
                this.data = value;
                notifyObservers();
            }
        }


        /// <summary>
        /// Increments the data by given amount
        /// </summary>
        /// <param name="value">Given value to increment the data by</param>
        public void incrementData(int value) {

            this.data += value;
            notifyObservers();

        }

        /// <summary>
        /// Allows a viewer to subscribe
        /// NOTE: Only objects of type IModelObserver can subscribe
        /// </summary>
        /// <param name="ModelObserverItem">The object that is subscribing to the model</param>
        public void addObserver(IModelObserver ModelObserverItem) {

            this.ModelObserverList.Add(ModelObserverItem);

        }

        /// <summary>
        /// Notifies all of the observers the data has been changed
        /// </summary>
        public void notifyObservers() {

            foreach (IModelObserver ModelObserverItem in this.ModelObserverList) {

                ModelObserverItem.update(this);

            }


        }

        /// <summary>
        /// Unsubscribes given observer from list
        /// </summary>
        /// <param name="ModelObserverItem">Given observer object to be unsubscribed</param>
        public void removeObserver(IModelObserver ModelObserverItem) {

            if (this.ModelObserverList != null) {

                this.ModelObserverList.Remove(ModelObserverItem);

            }

        }

        /// <summary>
        /// Removes all subscribers
        /// </summary>
        public void removeAllObservers() {

            if (this.ModelObserverList != null) {

                this.ModelObserverList.Clear();

            }

        }



    }

}