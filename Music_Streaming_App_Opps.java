import java.util.*;


abstract class MediaPlayer {
    abstract void play(Song song);
    abstract void pause();
    abstract void stop();
}


class Song {
    private String title;
    private String artist;
    private String category;

    public Song(String title, String artist, String category) {
        this.title = title;
        this.artist = artist;
        this.category = category;
    }

    public String getTitle() { return title; }
    public String getArtist() { return artist; }

    public void display() {
        System.out.println(title + " - " + artist + " (" + category + ")");
    }
}


class User {
    protected String name;
    public User(String name) { this.name = name; }
    public void showType() { System.out.println("Normal User"); }
}

class FreeUser extends User {
    public FreeUser(String name) { super(name); }
    public void showType() { System.out.println("Free User (Ads Enabled)"); }
}

class PremiumUser extends User {
    public PremiumUser(String name) { super(name); }
    public void showType() { System.out.println("Premium User (No Ads + Download)"); }
    public void download(Song song) {
        System.out.println("Downloading: " + song.getTitle());
    }
}


class Playlist {
    private String name;
    private List<Song> songs = new ArrayList<>();

    public Playlist(String name) { this.name = name; }

    public void addSong(Song song) {
        songs.add(song);
        System.out.println("Song added!");
    }

    public void showSongs() {
        System.out.println("Playlist: " + name);
        for (Song s : songs) s.display();
    }
}


class MusicPlayer extends MediaPlayer {
    void play(Song song) { System.out.println("Playing: " + song.getTitle()); }
    void pause() { System.out.println("Paused"); }
    void stop() { System.out.println("Stopped"); }
}


class Admin {
    private List<Song> songList;
    public Admin(List<Song> songList) { this.songList = songList; }

    public void addSong(Song song) {
        songList.add(song);
        System.out.println("Song added!");
    }

    public void viewSongs() {
        for (Song s : songList) s.display();
    }
}


public class Main {
    public static Song searchSong(List<Song> list, String key) {
        for (Song s : list) {
            if (s.getTitle().equalsIgnoreCase(key) || s.getArtist().equalsIgnoreCase(key))
                return s;
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Song> songs = new ArrayList<>();
        songs.add(new Song("Shape of You", "Ed Sheeran", "Pop"));
        songs.add(new Song("Believer", "Imagine Dragons", "Rock"));

        MusicPlayer player = new MusicPlayer();
        Playlist playlist = new Playlist("My Playlist");
        List<Song> recent = new ArrayList<>();

        System.out.println("1.Admin 2.User");
        int role = sc.nextInt();
        sc.nextLine();

        if (role == 1) {
            System.out.print("Password: ");
            if (!sc.nextLine().equals("admin123")) return;

            Admin admin = new Admin(songs);
            admin.viewSongs();
        } else {
            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.println("1.Free 2.Premium");
            User user = (sc.nextInt() == 1) ? new FreeUser(name) : new PremiumUser(name);

            int ch;
            do {
                System.out.println("1.Play 2.Playlist 3.Download 4.Recent 5.Exit");
                ch = sc.nextInt();

                switch (ch) {
                    case 1:
                        sc.nextLine();
                        System.out.print("Enter song: ");
                        Song s = searchSong(songs, sc.nextLine());
                        if (s != null) {
                            player.play(s);
                            recent.add(s);
                        }
                        break;

                    case 2:
                        playlist.addSong(songs.get(0));
                        break;

                    case 3:
                        if (user instanceof PremiumUser)
                            ((PremiumUser) user).download(songs.get(0));
                        else
                            System.out.println("Upgrade needed");
                        break;

                    case 4:
                        for (Song r : recent) r.display();
                        break;
                }
            } while (ch != 5);
        }
    }
}
