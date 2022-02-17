"""
Author: Ante Zovko
Version: 12/15/2020
Description: Implements absolute progression using inheritance
"""

class Progression:
  """Iterator producing a generic progression.

  Default iterator produces the whole numbers 0, 1, 2, ...
  """

  def __init__(self, start=0):
    """Initialize current to the first value of the progression."""
    self._current = start

  def _advance(self):
    """Update self._current to a new value.

    This should be overridden by a subclass to customize progression.

    By convention, if current is set to None, this designates the
    end of a finite progression.
    """
    self._current += 1

  def __next__(self):
    """Return the next element, or else raise StopIteration error."""
    if self._current is None:    # our convention to end a progression
      raise StopIteration()
    else:
      answer = self._current     # record current value to return
      self._advance()            # advance to prepare for next time
      return answer              # return the answer

  def __iter__(self):
    """By convention, an iterator must return itself as an iterator."""
    return self                  

  def print_progression(self, n):
    """Print next n values of the progression."""
    print(' '.join(str(next(self)) for j in range(n)))

class AbsoluteProgression(Progression):

    def __init__(self, first = 2, second = 200):
        
        super().__init__(first)
        self._prev = abs(first - second)
        
    def _advance(self):
        self._prev, self._current = self._current, abs(self._current - self._prev)


if __name__ == '__main__':
    AbsoluteProgression().print_progression(10)