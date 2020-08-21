package CS132Images;
/**
 * ImageTransformers can perform monadic transformations on SImage objects
 * 
 * @author David Levine
 * @version May 28, 2009
 */


import squint.SImage;

public interface ImageTransformer {
    public SImage transform(SImage picture);
}
