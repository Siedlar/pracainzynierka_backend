package pl.siedlarski.restfulworkout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.siedlarski.restfulworkout.dao.*;
import pl.siedlarski.restfulworkout.entity.*;
import pl.siedlarski.restfulworkout.payload.MessageResponse;
import pl.siedlarski.restfulworkout.payload.StworzTreningPayload;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/trening")
public class TreningController {
    @Autowired
    CwiczeniaRepository cwiczeniaRepository;
    @Autowired
    TreningRepository treningRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RekordRepository rekordRepository;
    @Autowired
    TypyTreninguRepository typyTreninguRepository;
    @Autowired
    HistoriaTreninguRepository historiaTreninguRepository;
    @Autowired
    HistoriaCwiczenRepository historiaCwiczenRepository;
    @Autowired
    JednoCwiczenieRepository jednoCwiczenieRepository;
    @PostMapping("/dodajTrening")
    public ResponseEntity<?> savePomiar(Principal principal,@RequestBody HistoriaTreningu historiaTreningu) {
        User user = userRepository.findByUsername(principal.getName()).get();
        historiaTreningu.setUser(user);
        System.out.println("DODAWANIE TRENINGU");
        System.out.println(historiaTreningu);
        for(HistoriaCwiczen historiaCwiczen:historiaTreningu.getHistoriaCwiczen()){
            historiaCwiczen.setHistoriaTreningu(historiaTreningu);
            for(JednoCwiczenie jednoCwiczenie:historiaCwiczen.getJednoCwiczenie()){
                jednoCwiczenie.setHistoriaCwiczen(historiaCwiczen);
            }

        }


       historiaTreninguRepository.save(historiaTreningu);
        updateRekordy(historiaTreningu);

 return ResponseEntity.ok().body(new MessageResponse("GITARA SIEMA"));
    }
    @GetMapping("/getTreningi")
    public ResponseEntity<?> getTreningi(Principal principal){
        User user=userRepository.findByUsername(principal.getName()).get();
        List<HistoriaTreningu> historiaTreningu=historiaTreninguRepository.findByUser(user);
 if(historiaTreningu.isEmpty()){
     return ResponseEntity.badRequest().body(new MessageResponse("Nie posiadasz jeszcze historii treningów"));
 }
        return ResponseEntity.ok().body(historiaTreningu);
    }
    @PostMapping("/getWybraneCwiczenie")
    public ResponseEntity<?> getWybraneCwiczenie(Principal principal,@RequestBody Cwiczenie cwiczenie){
        System.out.println(cwiczenie);
        User user = userRepository.findByUsername(principal.getName()).get();
        List<HistoriaTreningu> historiaTreningu=historiaTreninguRepository.findByUser(user);
        System.out.println(historiaTreningu);
//        List<HistoriaTreningu> historiaTreningu2=historiaTreningu.stream().filter(x->{
//                    for(HistoriaCwiczen historiaCwiczen1:x.getHistoriaCwiczen()){
//                        System.out.println(historiaCwiczen1);
//                      return  historiaCwiczen1.getCwiczenie().getCwiczenie_id() == cwiczenie.getCwiczenie_id();
//                    }
//                    return false;  }
//
//                ).collect(Collectors.toList());
        List<HistoriaTreningu> historiaTreningu2= new ArrayList<>();
        for(HistoriaTreningu historiaTreningu1:historiaTreningu){
            for(HistoriaCwiczen historiaCwiczen:historiaTreningu1.getHistoriaCwiczen()){
                if(historiaCwiczen.getCwiczenie().getCwiczenie_id()== cwiczenie.getCwiczenie_id()){
                    if( historiaTreningu2.contains(historiaTreningu1)){

                    }else{
                        HistoriaTreningu historiaTreningu3 = new HistoriaTreningu();
                        historiaTreningu3.setDataTreningu( historiaTreningu1.getDataTreningu());
                        historiaTreningu3.setTypTreningu(historiaTreningu1.getTypTreningu());
                        historiaTreningu3.setCzasTrwania(historiaTreningu1.getCzasTrwania());
                        historiaTreningu3.setNotatka(historiaTreningu1.getNotatka());
                        List<HistoriaCwiczen> historiaCwiczen2=new ArrayList<>();
                        historiaCwiczen2.add(historiaCwiczen);
                        historiaTreningu3.setHistoriaCwiczen(historiaCwiczen2);
                    historiaTreningu2.add(historiaTreningu3);}
                }
            }
        }
        System.out.println(historiaTreningu2);
//        List<HistoriaCwiczen> historiaCwiczen = new ArrayList<>();
//         historiaTreningu2.forEach(
//                x-> historiaCwiczen.addAll(x.getHistoriaCwiczen())
//        );
        System.out.println(historiaTreningu2);
if(historiaTreningu2.isEmpty()){
    return ResponseEntity.badRequest().body(new MessageResponse("Uzytkownik nie posiada historii z tym cwiczeniem"));
}
        return ResponseEntity.ok().body(historiaTreningu2);
    }
    @GetMapping("/getTypyTreningu")
    public ResponseEntity<?> getTypyTreningu(Principal principal){
        System.out.println("siema");
        List<TypTreningu> typTreningu=typyTreninguRepository.findAll();
        System.out.println(typTreningu);
        return ResponseEntity.ok().body(typTreningu);
    }
    @PostMapping("/deleteTreningi")
    public ResponseEntity<?> deletePomiar(Principal principal,@RequestBody HistoriaTreningu historiaTreningu){
        System.out.println(historiaTreningu);
        rekordRepository.deleteByHistoriaTreningu(historiaTreningu);
        historiaTreninguRepository.delete(historiaTreningu);

        return ResponseEntity.ok().body(new MessageResponse("Udalo sie usunac pomiar"));
    }
@GetMapping("/getPrzykladoweTreningi")
public ResponseEntity<?> getPrzykladoweTreningi() {
        List<Trening> listaTreningow =treningRepository.findAll();

    return ResponseEntity.ok().body(listaTreningow);
}
@PostMapping("/stworzTrening")
public ResponseEntity<?> stworzTrening(@RequestBody StworzTreningPayload stworzTreningPayload) {
    System.out.println(stworzTreningPayload);
    Random rand = new Random();
    List<Cwiczenie> listaCwiczen=cwiczeniaRepository.findAll();

    List<Cwiczenie> stworzonyTrening = new ArrayList<>();
    List<Cwiczenie> listaBiceps = listaCwiczen.stream().filter(x -> x.getPartia().getPartia().equals("Biceps")).collect(Collectors.toList());

    List<Cwiczenie> listaTriceps = listaCwiczen.stream().filter(x -> x.getPartia().getPartia().equals("Triceps")).collect(Collectors.toList());
    List<Cwiczenie> listaPlecy = listaCwiczen.stream().filter(x -> x.getPartia().getPartia().equals("Plecy")).collect(Collectors.toList());
    List<Cwiczenie> listaKaptury = listaCwiczen.stream().filter(x -> x.getPartia().getPartia().equals("Kaptury")).collect(Collectors.toList());
    List<Cwiczenie> listaPosladki = listaCwiczen.stream().filter(x -> x.getPartia().getPartia().equals("Posladki")).collect(Collectors.toList());
    List<Cwiczenie> listaNogi = listaCwiczen.stream().filter(x -> x.getPartia().getPartia().equals("Nogi")).collect(Collectors.toList());
    List<Cwiczenie> listaBrzuch= listaCwiczen.stream().filter(x -> x.getPartia().getPartia().equals("Brzuch")).collect(Collectors.toList());
    List<Cwiczenie> listaPrzedramie= listaCwiczen.stream().filter(x -> x.getPartia().getPartia().equals("Przedramie")).collect(Collectors.toList());
    List<Cwiczenie> listaBarki = listaCwiczen.stream().filter(x -> x.getPartia().getPartia().equals("Barki")).collect(Collectors.toList());
    List<Cwiczenie> listaKlatka = listaCwiczen.stream().filter(x -> x.getPartia().getPartia().equals("Klatka")).collect(Collectors.toList());
    System.out.println(stworzTreningPayload.getTypTreningu().getTypTreningu());
    if (stworzTreningPayload.getTypTreningu().getTypTreningu().equals("FBW")) {


        stworzonyTrening.add(listaBiceps.get(rand.nextInt(listaBiceps.size())));
        stworzonyTrening.add(listaTriceps.get(rand.nextInt(listaTriceps.size())));
        stworzonyTrening.add(listaPlecy.get(rand.nextInt(listaPlecy.size())));
        stworzonyTrening.add(listaKaptury.get(rand.nextInt(listaKaptury.size())));
        stworzonyTrening.add(listaPosladki.get(rand.nextInt(listaPosladki.size())));
        stworzonyTrening.add(listaNogi.get(rand.nextInt(listaNogi.size())));
        stworzonyTrening.add(listaBrzuch.get(rand.nextInt(listaBrzuch.size())));
        stworzonyTrening.add(listaPrzedramie.get(rand.nextInt(listaPrzedramie.size())));
        stworzonyTrening.add(listaBarki.get(rand.nextInt(listaBarki.size())));
        stworzonyTrening.add(listaKlatka.get(rand.nextInt(listaKlatka.size())));

    } else if (stworzTreningPayload.getTypTreningu().getTypTreningu().equals("SPLIT")) {
        if(stworzTreningPayload.getSplit() == 1){

            for(int i=0;i<4;i++){
                int zmienna=rand.nextInt(listaKlatka.size());
                stworzonyTrening.add(listaKlatka.get(zmienna));
                listaKlatka.remove(zmienna);
            }



            for(int i=0;i<3;i++){
                int zmienna=rand.nextInt(listaBiceps.size());
                stworzonyTrening.add(listaBiceps.get(zmienna));
                listaBiceps.remove(zmienna);
            }


            }else if(stworzTreningPayload.getSplit() == 2){
            for(int i=0;i<4;i++){
                int zmienna=rand.nextInt(listaPlecy.size());
                stworzonyTrening.add(listaPlecy.get(zmienna));
                listaPlecy.remove(zmienna);
            }



            for(int i=0;i<3;i++){
                int zmienna=rand.nextInt(listaTriceps.size());
                stworzonyTrening.add(listaTriceps.get(zmienna));
                listaTriceps.remove(zmienna);
            }



        }else if(stworzTreningPayload.getSplit() == 3){

            for(int i=0;i<4;i++){
                int zmienna=rand.nextInt(listaNogi.size());
                stworzonyTrening.add(listaNogi.get(zmienna));
                listaNogi.remove(zmienna);
            }



            for(int i=0;i<3;i++){
                int zmienna=rand.nextInt(listaBarki.size());
                stworzonyTrening.add(listaBarki.get(zmienna));
                listaBarki.remove(zmienna);
            }


        }
   }

    return ResponseEntity.ok().body(stworzonyTrening);
}
    @GetMapping("/getRekordy")
    public ResponseEntity<?> getRekordy(Principal principal){
        User user=userRepository.findByUsername(principal.getName()).get();
       List<Rekord> listaRekordow= rekordRepository.findByUser(user);
       if(listaRekordow.isEmpty()){
           return ResponseEntity.badRequest().body(new MessageResponse("Nie masz jeszcze zapisanych rekordow"));
       }
        return ResponseEntity.ok().body(listaRekordow);
    }

    public void updateRekordy(HistoriaTreningu historiaTreningu){
        System.out.println("UPDATE stan");
        ArrayList<Rekord> listaRekordow=new ArrayList();
        for(HistoriaCwiczen historiaCwiczen:historiaTreningu.getHistoriaCwiczen()){
            Rekord rekord= rekordRepository.findByUserAndCwiczenie(historiaTreningu.getUser(), historiaCwiczen.getCwiczenie());
            if(rekord == null){
                rekord=new Rekord();
                rekord.setUser(historiaTreningu.getUser());
                rekord.setCwiczenie(historiaCwiczen.getCwiczenie());
             rekord.setMaks_czas_trwania(historiaCwiczen.getCzasTrwaniaCwiczenia());
           for(JednoCwiczenie jednoCwiczenie:historiaCwiczen.getJednoCwiczenie()){
               rekord.setMaks_czas(jednoCwiczenie.getCzas());
               rekord.setMaks_ciezar(jednoCwiczenie.getCiezar());
               rekord.setMaks_kilometry(jednoCwiczenie.getKilometry());
               rekord.setMaks_seria(jednoCwiczenie.getSeria());
               rekord.setMaks_powtorzenia(jednoCwiczenie.getPowtorzenia());
           }
                listaRekordow.add(rekord);

            }else{
            if(rekord.getMaks_czas_trwania() < historiaCwiczen.getCzasTrwaniaCwiczenia()){
                rekord.setMaks_czas_trwania(historiaCwiczen.getCzasTrwaniaCwiczenia());
            }

            for(JednoCwiczenie jednoCwiczenie:historiaCwiczen.getJednoCwiczenie()){
                if(jednoCwiczenie.getCiezar() != null){
                    if(rekord.getMaks_ciezar() < jednoCwiczenie.getCiezar()){
                        rekord.setMaks_ciezar(jednoCwiczenie.getCiezar());
                    }
                }

if(jednoCwiczenie.getCzas()!= null){
    if(rekord.getMaks_czas() < jednoCwiczenie.getCzas()){
        rekord.setMaks_czas(jednoCwiczenie.getCzas());
    }
}

        if(jednoCwiczenie.getKilometry() != null ){
            if(rekord.getMaks_kilometry()< jednoCwiczenie.getKilometry()){
                rekord.setMaks_kilometry(jednoCwiczenie.getKilometry());
            }
        }
             if(jednoCwiczenie.getPowtorzenia() != null){
                 if(rekord.getMaks_powtorzenia()< jednoCwiczenie.getPowtorzenia()){
                     rekord.setMaks_powtorzenia(jednoCwiczenie.getPowtorzenia());
                 }
             }
              if(jednoCwiczenie.getSeria() !=null){
                  if(rekord.getMaks_seria()<jednoCwiczenie.getSeria()){
                      rekord.setMaks_seria(jednoCwiczenie.getSeria());
                  }
              }


            }



            rekord.setHistoriaTreningu(historiaTreningu);
            listaRekordow.add(rekord);


            }
}
        if(!listaRekordow.isEmpty()){
            rekordRepository.saveAll(listaRekordow);
        }

    }
}
