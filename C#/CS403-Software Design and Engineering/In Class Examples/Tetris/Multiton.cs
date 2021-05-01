using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp6 {
  public enum MarkerColor {
    BLUE,
    RED,
    ORANGE,
  }

  public class Marker {
    private static Dictionary<MarkerColor, Marker> instances = 
      new Dictionary<MarkerColor, Marker>();
    public string color;

    private Marker(string color) {
      this.color = color;
    }

    public static Marker GetInstance(MarkerColor color) {
      if (!instances.ContainsKey(color)) {
        instances[color] = new Marker(color.ToString());
      }
      return instances[color];
    }
  }
}
