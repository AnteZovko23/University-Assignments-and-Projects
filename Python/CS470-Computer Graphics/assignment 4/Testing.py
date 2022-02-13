import Illumination_Model

surface_normal = [0, 5, 0]
light_vector = [1, 1, 0]

ip = 0.5
kd = 0.5

d = 1

light = Illumination_Model.Illumination_Model(surface_normal=surface_normal, light_vector=light_vector, point_light_intensity=ip, diffuse_constant=kd, distance=d)

print(light.triColorHexCode())