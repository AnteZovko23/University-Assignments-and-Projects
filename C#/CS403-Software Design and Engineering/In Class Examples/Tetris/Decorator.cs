using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp6 {
  /// Component
  public abstract class Pizza {
    public abstract string GetToppings();
    public void Show() {
      Console.WriteLine("Our pizza contains " + GetToppings());
    }
  }

  /// Concrete Component
  public class CheesePizza : Pizza {
    public override string GetToppings() {
      Console.WriteLine("Adding the cheese");
      return "cheese";
    }
  }

  /// Decorator
  public abstract class Decorator : Pizza {
    private Pizza pizzaBefore;

    public Decorator(Pizza pizza) {
      pizzaBefore = pizza;
    }

    public override string GetToppings() {
      return pizzaBefore.GetToppings();
    }
  }

  /// Concrete Decorator
  public class PepperoniDecorator : Decorator {
    public PepperoniDecorator(Pizza pizza) : base(pizza) {}
    public override string GetToppings() {
      Console.WriteLine("Adding the pepperoni");
      return base.GetToppings() + ", pepperoni";
    }
  }
  public class MushroomsDecorator : Decorator {
    public MushroomsDecorator(Pizza pizza) : base(pizza) { }
    public override string GetToppings() {
      Console.WriteLine("Adding the mushrooms");
      return base.GetToppings() + ", mushrooms";
    }
  }
}
