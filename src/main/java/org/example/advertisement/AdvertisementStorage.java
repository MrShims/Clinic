package org.example.advertisement;

import org.example.ConsoleHelper;

public class AdvertisementStorage extends Thread
{

    private  static AdvertisementStorage advertisementStorage = new AdvertisementStorage();


    private AdvertisementStorage()
    {
        this.setDaemon(true);
    }

    @Override
    public void run() {

        try {
            while (true) {
                Thread.sleep(10000);

                ConsoleHelper.Print("Скидка");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

    public static AdvertisementStorage getInstance()
    {
        advertisementStorage.start();
        return advertisementStorage;

    }







}
