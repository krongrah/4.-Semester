/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import common.Entity;
import data.GameData;
import data.World;
import game.core.GameInputProcessor;
import game.core.GameStateManager;
import game.renderer.Renderer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import services.IPluginService;
import services.IPostProcessor;
import services.IProcessor;
import services.IRenderer;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class PlayState extends GameState {
    
    private final Lookup lookup = Lookup.getDefault();
    private Lookup.Result<IPluginService> result;
    private Lookup.Result<IPostProcessor> postProcessorResults;
    private List<IPluginService> gamePlugins = new CopyOnWriteArrayList<>();
    private List<IPostProcessor> postProcessors = new ArrayList<>();
    private List<IProcessor> entityProcessors = new ArrayList<>();

    private static OrthographicCamera cam;
    private ShapeRenderer sr;
    private IRenderer renderer;
    
    private GameData gameData;
    private World world;
    
    public PlayState(GameStateManager gameStateManager) {
        
        super(gameStateManager);
    }
    
    @Override
    public void init(GameData gameData, World world) {
        this.gameData = gameData;
        this.world = world;
        renderer = new Renderer(world);
        renderer.setBackgroundColor(125, 190, 225, 1);

        gameData.setDisplayWidth(Gdx.graphics.getWidth() * 2);
        gameData.setDisplayHeight(Gdx.graphics.getHeight() * 2);

        cam = new OrthographicCamera(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        cam.translate(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        cam.update();

        sr = new ShapeRenderer();

        result = lookup.lookupResult(IPluginService.class);
        result.addLookupListener(lookupListener);
        result.allItems();

        Gdx.input.setInputProcessor(
                new GameInputProcessor(gameData)
        );

//        IGamePluginService playerPlugin = new PlayerPlugin();
//        IEntityProcessingService playerProcess = new PlayerControlSystem();
//        entityPlugins.add(playerPlugin);
//        entityProcessors.add(playerProcess);
        // Lookup all Game Plugins using ServiceLoader
        for (IPluginService iGamePlugin : result.allInstances()) {
            iGamePlugin.start(gameData, world);
            gamePlugins.add(iGamePlugin);
            renderer.loadTexture(iGamePlugin.getPath());
        }

        postProcessorResults = lookup.lookupResult(IPostProcessor.class);
        postProcessorResults.addLookupListener(lookupListener);
        postProcessorResults.allItems();
        for (IPostProcessor pp : postProcessorResults.allInstances()) {
            postProcessors.add(pp);
        }
    }

    @Override
    public void update() {
        // Update
        for (IProcessor entityProcessorService : getEntityProcessingServices()) {
            entityProcessorService.process(gameData, world);
        }
        
        for (IPostProcessor postProcessor : postProcessors) {
            postProcessor.process(gameData, world);
        }
    }

    @Override
    public void draw() {
        for (Entity entity : world.getEntities()) {

        }
        
        this.render();
    }
    
    private void render() {
        renderer.render(world, gameData);
    }

    @Override
    public void handleInput() {
        gameData.getKeys().update();
    }

    @Override
    public void dispose() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private final LookupListener lookupListener = new LookupListener() {
        @Override
        public void resultChanged(LookupEvent le) {

            Collection<? extends IPluginService> updated = result.allInstances();

            for (IPluginService us : updated) {
                // Newly installed modules
                if (!gamePlugins.contains(us)) {
                    us.start(gameData, world);
                    gamePlugins.add(us);
                }
            }

            // Stop and remove module
            for (IPluginService gs : gamePlugins) {
                if (!updated.contains(gs)) {
                    gs.stop(gameData, world);
                    gamePlugins.remove(gs);
                }
            }
        }

    };
    
    private Collection<? extends IProcessor> getEntityProcessingServices() {
        return lookup.lookupAll(IProcessor.class);
//        return SPILocator.locateAll(IProcessor.class);
    }

    
}
