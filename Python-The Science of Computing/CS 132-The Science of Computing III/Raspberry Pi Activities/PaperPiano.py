######################################################################
# Name: Ante Zovko
# Date: 5/13/2020
# Description: Using pygame sound features demonstrates square, triangle
#              and sawtooth sound wave form generation
######################################################################
import pygame
from time import sleep
from array import array

MIXER_FREQ = 44100
MIXER_SIZE = -16
MIXER_CHANS = 1
MIXER_BUFF = 1024

# Note Class
class Note(pygame.mixer.Sound):
    def __init__(self, frequency, volume, form):
        self.frequency = frequency
        self.form = form
        pygame.mixer.Sound.__init__(self, buffer=self.build_samples())
        self.set_volume(volume)
        
    # Builds the sound waves
    def build_samples(self):
        period = int(round(MIXER_FREQ/self.frequency))
        amplitude = 2**(abs(MIXER_SIZE)-1)-1
        samples = array("h", [0]*period)

        # Square sound wave model
        if(self.form == "square"):
            for t in range(period):
                if(t < period/2):
                    samples[t] = amplitude
                else:
                    samples[t] = -amplitude

        # Triangle sound wave model
        # 762 is the calculated slope
        # It increases from start until a quarter of the period
        # Decreases until the 3/4 of the period
        # Increases again
        elif(self.form == "triangle"):
            amplitude = 0
            for t in range(period):
                if(t < period/4 or t > period*(3/4)):
                    amplitude += 762
                    samples[t] = amplitude
                else:
                    amplitude -= 762
                    samples[t] = amplitude

        # Sawtooth sound wave model
        # 390 is the clalculated slope
        # It increases from start until a half of the period
        # Then it drops to the negative value of the current amplitude
        # Then increases again until the end of the period
        elif(self.form == "sawtooth"):
            amplitude = 0
            for t in range(period):
                if(t == int(period/2)):
                    amplitude = -amplitude
                    samples[t] = amplitude
                else:
                    amplitude += 390
                    samples[t] = amplitude
            

        return samples


# Main
form = raw_input("Form: ")
pygame.mixer.pre_init(MIXER_FREQ, MIXER_SIZE,MIXER_CHANS,MIXER_BUFF)
pygame.display.set_mode((200,100))
pygame.init()


note = Note(261.6, 1,form)

note.play(-1)
sleep(1)
note.stop()
pygame.quit()




