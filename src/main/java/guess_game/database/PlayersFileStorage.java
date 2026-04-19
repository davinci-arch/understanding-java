package guess_game.database;

import guess_game.Player;
import guess_game.exceptions.OutRootDirectoryScope;
import org.apache.commons.io.channels.FileChannels;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.*;

public class PlayersFileStorage<T extends Player> implements Storage<T> {

    private Path basicPath = Path.of("/Users/oleksandrparfesa");
    private Path rootDirectory;
    private Path pathToPlayers;
    public PlayersFileStorage(String rootDirectoryPath) {
        rootDirectory = Path.of(rootDirectoryPath);
        basicPath = basicPath.resolve(rootDirectory.getFileName());
    }

    private void createRootDirectory(Path rootDirectory) throws IOException {
        if (!Files.exists(rootDirectory)) {
            var path = Files.createFile(rootDirectory);
            Map<String, String> attributes = Map.of("type", "working");
            setAllAttributes(attributes, path);
            Files.createDirectory(path);
            System.out.println("Directory was successfully created");
        } else {
            System.out.printf("Directory with this path %s is already exists %n",
                    rootDirectory.toAbsolutePath());
        }
    }

    private void setAllAttributes(Map<String, String> customAttributes, Path pathToFile) throws IOException {
        var attributesView = Files.getFileAttributeView(pathToFile, UserDefinedFileAttributeView.class);
        for (Map.Entry<String, String> entry : customAttributes.entrySet()) {
            attributesView.write(entry.getKey(), StandardCharsets.UTF_8.encode(entry.getValue()));
        }
    }

    private Path createFile(Path pathToFile) throws IOException {
        if (!isRootDirectory(pathToFile)) {
            throw new OutRootDirectoryScope("U trying to create file outside the working directory");
        }
        return Files.createFile(pathToFile);
    }

    private boolean isRootDirectory(Path pathToRootDir) throws IOException {
        var attributes = Files.readAttributes(pathToRootDir, "*");
        return attributes.containsKey("type");
    }

    public void initializeStorage(Path rootDirectory, Path pathToPlayers) throws IOException {
        createRootDirectory(rootDirectory);
        createFile(pathToPlayers);
        this.pathToPlayers = pathToPlayers;
    }

    @Override
    public boolean save(T t) {
        try(ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(pathToPlayers))) {
            out.writeObject(t);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public Optional<T> load(String matcherLine) {

        try(ObjectInputStream read = new ObjectInputStream(Files.newInputStream(pathToPlayers))) {
            return Optional.of((T)read.readObject());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    //TODO READ all serialized data in file
    @Override
    public Map<UUID, T> loadAll() {
        return Map.of();
    }


}
