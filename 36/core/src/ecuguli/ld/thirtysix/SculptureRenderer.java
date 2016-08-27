package ecuguli.ld.thirtysix;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;

public class SculptureRenderer {
    private FrameBuffer colorBuffer;
    private Sculpture   sculpture;
    private ModelBatch  modelBatch;
    private ModelInstance model;

    public SculptureRenderer(Sculpture sculpture, int width, int height){
        colorBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, width, height, false);
        modelBatch = new ModelBatch();
        this.sculpture = sculpture;
    }

    public Texture renderSculpture(Camera camera){
        colorBuffer.bind();
        modelBatch.begin(camera);
        modelBatch.render(model);
        colorBuffer.end();
    }

    private Model fromSculpture(){
        float size = 1;
        ModelBuilder builder = new ModelBuilder();
        builder.begin();
        for(int x = 0; x < sculpture.getWidth(); x++)
            for(int y = 0; y < sculpture.getHeight(); y++)
                for(int z = 0; z < sculpture.getLength(); z++)
                if(sculpture.get(x, y, z) != 0){
                    float color = sculpture.get(x, y, z);
                    System.out.println(color);
                    Node node = builder.node();
                    node.id = x + " " + y + " " + z;
                    node.translation.set(x, y, z);

                    MeshPartBuilder meshBuilder;
                    meshBuilder = builder.part("part_" + x + "_" + y + "_" + z, GL20.GL_TRIANGLES, VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal, new Material());
                    meshBuilder.setColor(new Color(color, color, color, 255f));
                    {
                        MeshPartBuilder.VertexInfo v1 = new MeshPartBuilder.VertexInfo().setCol(color, color, color, 255f).setPos(1, 1, 0).setNor(0, 0, 1);
                        MeshPartBuilder.VertexInfo v2 = new MeshPartBuilder.VertexInfo().setCol(color, color, color, 255f).setPos(0, 1, 0).setNor(0, 0, 1);
                        MeshPartBuilder.VertexInfo v3 = new MeshPartBuilder.VertexInfo().setCol(color, color, color, 255f).setPos(0, 1, 1).setNor(0, 0, 1);
                        MeshPartBuilder.VertexInfo v4 = new MeshPartBuilder.VertexInfo().setCol(color, color, color, 255f).setPos(1, 1, 1).setNor(0, 0, 1);
                        meshBuilder.rect(v1, v2, v3, v4);
                    }{
                        Node nodeRotate = builder.node();
                        nodeRotate.id = x + " " + y + " " + z + "_back";
                        nodeRotate.rotation.setEulerAngles(90, 0, 0);
                        MeshPartBuilder.VertexInfo v1 = new MeshPartBuilder.VertexInfo().setCol(color, color, color, 255f).setPos(1, 0, 0).setNor(0, 0, 1);
                        MeshPartBuilder.VertexInfo v2 = new MeshPartBuilder.VertexInfo().setCol(color, color, color, 255f).setPos(0, 0, 1).setNor(0, 0, 1);
                        MeshPartBuilder.VertexInfo v3 = new MeshPartBuilder.VertexInfo().setCol(color, color, color, 255f).setPos(0, 0, 0).setNor(0, 0, 1);
                        MeshPartBuilder.VertexInfo v4 = new MeshPartBuilder.VertexInfo().setCol(color, color, color, 255f).setPos(1, 0, 0).setNor(0, 0, 1);
                        meshBuilder.rect(v1, v2, v3, v4);
                    }{
                        Node nodeRotate = builder.node();
                        nodeRotate.id = x + " " + y + " " + z + "_right";
                        nodeRotate.rotation.setEulerAngles(0, 90, 0);
                        MeshPartBuilder.VertexInfo v1 = new MeshPartBuilder.VertexInfo().setCol(color, color, color, 255f).setPos(1, 1, 1).setNor(0, 0, 1);
                        MeshPartBuilder.VertexInfo v2 = new MeshPartBuilder.VertexInfo().setCol(color, color, color, 255f).setPos(0, 1, 1).setNor(0, 0, 1);
                        MeshPartBuilder.VertexInfo v3 = new MeshPartBuilder.VertexInfo().setCol(color, color, color, 255f).setPos(0, 0, 1).setNor(0, 0, 1);
                        MeshPartBuilder.VertexInfo v4 = new MeshPartBuilder.VertexInfo().setCol(color, color, color, 255f).setPos(1, 0, 1).setNor(0, 0, 1);
                        meshBuilder.rect(v1, v2, v3, v4);
                    }{
                        Node nodeRotate = builder.node();
                        nodeRotate.id = x + " " + y + " " + z + "_left";
                        nodeRotate.rotation.setEulerAngles(0, 90, 0);
                        MeshPartBuilder.VertexInfo v1 = new MeshPartBuilder.VertexInfo().setCol(color, color, color, 255f).setPos(1, 0, 0).setNor(0, 0, 1);
                        MeshPartBuilder.VertexInfo v2 = new MeshPartBuilder.VertexInfo().setCol(color, color, color, 255f).setPos(0, 0, 0).setNor(0, 0, 1);
                        MeshPartBuilder.VertexInfo v3 = new MeshPartBuilder.VertexInfo().setCol(color, color, color, 255f).setPos(0, 1, 0).setNor(0, 0, 1);
                        MeshPartBuilder.VertexInfo v4 = new MeshPartBuilder.VertexInfo().setCol(color, color, color, 255f).setPos(1, 1, 0).setNor(0, 0, 1);
                        meshBuilder.rect(v1, v2, v3, v4);
                    }{
                        Node nodeRotate = builder.node();
                        nodeRotate.id = x + " " + y + " " + z + "_top";
                        MeshPartBuilder.VertexInfo v1 = new MeshPartBuilder.VertexInfo().setCol(color, color, color, 255f).setPos(0, 1, 1).setNor(0, 0, 1);
                        MeshPartBuilder.VertexInfo v2 = new MeshPartBuilder.VertexInfo().setCol(color, color, color, 255f).setPos(0, 1, 0).setNor(0, 0, 1);
                        MeshPartBuilder.VertexInfo v3 = new MeshPartBuilder.VertexInfo().setCol(color, color, color, 255f).setPos(0, 0, 0).setNor(0, 0, 1);
                        MeshPartBuilder.VertexInfo v4 = new MeshPartBuilder.VertexInfo().setCol(color, color, color, 255f).setPos(0, 0, 1).setNor(0, 0, 1);
                        meshBuilder.rect(v1, v2, v3, v4);
                    }{
                        Node nodeRotate = builder.node();
                        nodeRotate.id = x + " " + y + " " + z + "_bottom";
                        MeshPartBuilder.VertexInfo v1 = new MeshPartBuilder.VertexInfo().setCol(color, color, color, 255f).setPos(1, 1, 0).setNor(0, 0, 1);
                        MeshPartBuilder.VertexInfo v2 = new MeshPartBuilder.VertexInfo().setCol(color, color, color, 255f).setPos(1, 1, 1).setNor(0, 0, 1);
                        MeshPartBuilder.VertexInfo v3 = new MeshPartBuilder.VertexInfo().setCol(color, color, color, 255f).setPos(1, 0, 1).setNor(0, 0, 1);
                        MeshPartBuilder.VertexInfo v4 = new MeshPartBuilder.VertexInfo().setCol(color, color, color, 255f).setPos(1, 0, 0).setNor(0, 0, 1);
                        meshBuilder.rect(v1, v2, v3, v4);
                    }
                }
        return builder.end();
    }
}
