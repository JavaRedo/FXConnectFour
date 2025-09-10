package com.javaredo.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;

import com.javaredo.model.GameModel;
//import java.io.;

public class SaveManager implements ISaveManager {

    // private  final String DEFAULT_SAVE_DIR = "saves";
    // private  final int DEFAULT_SAVE_SLOTS = 20;


    private final Path saveRoot;
    private final int numSaveSlots;


    public SaveManager() {
        this.numSaveSlots = 20;
        this.saveRoot = Paths.get("saves");
    }


    public void initialize(){

        //read all save files
        try{
            initialLoadSaves();
        }
        catch(Exception exception){
            exception.printStackTrace();
        }
        //add empty save files if num of save files
        // less than numSaveSlots

    }


    private void initialLoadSaves() throws IOException{
            Files.createDirectories(saveRoot);
            this.fillEmptySlots();
    }

    private void fillEmptySlots() {
        for(int savePos=0;savePos < numSaveSlots;savePos++){

            Save s = new Save(savePos); 

            Path file = saveRoot.resolve(String.format("slot-%02d.obj", savePos));
            try (ObjectOutputStream oos =
                     new ObjectOutputStream(
                         new BufferedOutputStream(
                             Files.newOutputStream(file,
                                 StandardOpenOption.CREATE_NEW,
                                 StandardOpenOption.WRITE)))) {

                oos.writeObject(s);
                System.out.println("created new empty save slot-" +savePos );
                oos.flush();
            } catch (Exception e) {
                if(e.getClass() == FileAlreadyExistsException.class){
                    System.out.println("slot- " + savePos + ".obj already exists");
                }
            }
        }

    }

    private Save readSave(Path file) {
        try (ObjectInputStream ois =
                 new ObjectInputStream(
                     new BufferedInputStream(
                         Files.newInputStream(file, StandardOpenOption.READ)))) {

            Object obj = ois.readObject();
            return (Save) obj;  // cast back to Save

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean save(GameModel model,int savePos) {

        Path file = saveRoot.resolve(String.format("slot-%02d.obj", savePos));

        Save s = readSave(file);

        s.setModel(model);
        s.setDate(LocalDateTime.now());
        
            try (ObjectOutputStream oos =
                     new ObjectOutputStream(
                         new BufferedOutputStream(
                             Files.newOutputStream(file,
                                 StandardOpenOption.CREATE,
                                 StandardOpenOption.TRUNCATE_EXISTING,
                                 StandardOpenOption.WRITE)))) {

                oos.writeObject(s);
                oos.flush();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

        return true;
    }

    @Override
    public GameModel load(int savePos) {


        Path file = saveRoot.resolve(String.format("slot-%02d.obj", savePos));

        Save s = readSave(file);

        return s.getModel();
    }


    public Save[] getSaves(){
        Save[] saves = new Save[this.numSaveSlots];

        for (int savePos = 0; savePos < saves.length; savePos++) {
            Path file = saveRoot.resolve(String.format("slot-%02d.obj", savePos));
            Save s = readSave(file);

            saves[savePos] = s;
        }

        return saves;
    }


    public void deleteSave(int savePos) {

        Path file = saveRoot.resolve(String.format("slot-%02d.obj", savePos));
        
        Save s = new Save(savePos);

        try (ObjectOutputStream oos =
                     new ObjectOutputStream(
                         new BufferedOutputStream(
                             Files.newOutputStream(file,
                                 StandardOpenOption.CREATE,
                                 StandardOpenOption.TRUNCATE_EXISTING,
                                 StandardOpenOption.WRITE)))) {

                oos.writeObject(s);
                oos.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        
    }

}
