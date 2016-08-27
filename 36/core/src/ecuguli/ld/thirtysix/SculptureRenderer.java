package ecuguli.ld.thirtysix;

import com.badlogic.gdx.Gdx;
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
    private OrthographicCamera camera;

    public SculptureRenderer(Sculpture sculpture, int width, int height){
        colorBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, width, height, true);
        modelBatch = new ModelBatch();
        this.sculpture = sculpture;
    }

    public Texture renderSculpture(){
        camera = new OrthographicCamera(sculpture.getWidth() + 10, sculpture.getHeight() + 10);
        camera.position.set(sculpture.getHeight() * 4, sculpture.getHeight() * 4, sculpture.getHeight() * 4);
        camera.lookAt(0, 0, 0);
        camera.near = 0;
        camera.far  = 10000;

        model = new ModelInstance(fromSculpture());

        colorBuffer.bind();
        {
            camera.update();
            Gdx.gl.glClearColor(0, 0, 0, 0);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
            Gdx.gl.glViewport(0, 0, colorBuffer.getWidth(), colorBuffer.getHeight());
            modelBatch.begin(camera);
            modelBatch.render(model);
            modelBatch.end();
        }
        colorBuffer.end();
        return colorBuffer.getColorBufferTexture();
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
                        MeshPartBuilder.VertexInfo v1 = new MeshPartBuilder.VertexInfo().setCol(color, color, color, 255f).setPos(1, 0, 0).setNor(0, 0, 1);
                        MeshPartBuilder.VertexInfo v2 = new MeshPartBuilder.VertexInfo().setCol(color, color, color, 255f).setPos(0, 0, 1).setNor(0, 0, 1);
                        MeshPartBuilder.VertexInfo v3 = new MeshPartBuilder.VertexInfo().setCol(color, color, color, 255f).setPos(0, 0, 0).setNor(0, 0, 1);
                        MeshPartBuilder.VertexInfo v4 = new MeshPartBuilder.VertexInfo().setCol(color, color, color, 255f).setPos(1, 0, 0).setNor(0, 0, 1);
                        meshBuilder.rect(v1, v2, v3, v4);
                    }{
                        MeshPartBuilder.VertexInfo v1 = new MeshPartBuilder.VertexInfo().setCol(color, color, color, 255f).setPos(1, 1, 1).setNor(0, 0, 1);
                        MeshPartBuilder.VertexInfo v2 = new MeshPartBuilder.VertexInfo().setCol(color, color, color, 255f).setPos(0, 1, 1).setNor(0, 0, 1);
                        MeshPartBuilder.VertexInfo v3 = new MeshPartBuilder.VertexInfo().setCol(color, color, color, 255f).setPos(0, 0, 1).setNor(0, 0, 1);
                        MeshPartBuilder.VertexInfo v4 = new MeshPartBuilder.VertexInfo().setCol(color, color, color, 255f).setPos(1, 0, 1).setNor(0, 0, 1);
                        meshBuilder.rect(v1, v2, v3, v4);
                    }{
                        MeshPartBuilder.VertexInfo v1 = new MeshPartBuilder.VertexInfo().setCol(color, color, color, 255f).setPos(1, 0, 0).setNor(0, 0, 1);
                        MeshPartBuilder.VertexInfo v2 = new MeshPartBuilder.VertexInfo().setCol(color, color, color, 255f).setPos(0, 0, 0).setNor(0, 0, 1);
                        MeshPartBuilder.VertexInfo v3 = new MeshPartBuilder.VertexInfo().setCol(color, color, color, 255f).setPos(0, 1, 0).setNor(0, 0, 1);
                        MeshPartBuilder.VertexInfo v4 = new MeshPartBuilder.VertexInfo().setCol(color, color, color, 255f).setPos(1, 1, 0).setNor(0, 0, 1);
                        meshBuilder.rect(v1, v2, v3, v4);
                    }{
                        MeshPartBuilder.VertexInfo v1 = new MeshPartBuilder.VertexInfo().setCol(color, color, color, 255f).setPos(0, 1, 1).setNor(0, 0, 1);
                        MeshPartBuilder.VertexInfo v2 = new MeshPartBuilder.VertexInfo().setCol(color, color, color, 255f).setPos(0, 1, 0).setNor(0, 0, 1);
                        MeshPartBuilder.VertexInfo v3 = new MeshPartBuilder.VertexInfo().setCol(color, color, color, 255f).setPos(0, 0, 0).setNor(0, 0, 1);
                        MeshPartBuilder.VertexInfo v4 = new MeshPartBuilder.VertexInfo().setCol(color, color, color, 255f).setPos(0, 0, 1).setNor(0, 0, 1);
                        meshBuilder.rect(v1, v2, v3, v4);
                    }{
                        MeshPartBuilder.VertexInfo v1 = new MeshPartBuilder.VertexInfo().setCol(color, color, color, 255f).setPos(1, 1, 0).setNor(0, 0, 1);
                        MeshPartBuilder.VertexInfo v2 = new MeshPartBuilder.VertexInfo().setCol(color, color, color, 255f).setPos(1, 1, 1).setNor(0, 0, 1);
                        MeshPartBuilder.VertexInfo v3 = new MeshPartBuilder.VertexInfo().setCol(color, color, color, 255f).setPos(1, 0, 1).setNor(0, 0, 1);
                        MeshPartBuilder.VertexInfo v4 = new MeshPartBuilder.VertexInfo().setCol(color, color, color, 255f).setPos(1, 0, 0).setNor(0, 0, 1);
                        meshBuilder.rect(v1, v2, v3, v4);
                    }
                }
        return builder.end();
    }

    public void dispose(){
        modelBatch.dispose();
    }
}
