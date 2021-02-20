package pl.siedlarski.restfulworkout.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class TreningControllerTest {
    @Autowired
    private MockMvc mvc;
    @Test
    void savePomiar() throws Exception {
        String username = "krysie";
        String password = "qwerty";

        String body = "{\"username\":\"" + username + "\", \"password\":\""
                + password + "\"}";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/auth/signin").contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk()).andReturn();

        body="{\"historiaCwiczen\":[{\"cwiczenie\":{\"cwiczenie_id\":138,\"nazwa_cwiczenia\":\"Squash\",\"url_film\":\"https://www.youtube.com/embed/pHkSz77CUp4\",\"trudnosc\":{\"trudnosc_id\":2,\"trudnosc\":\"średniozaawansowany\"},\"ekwipunek\":[{\"ekwipunek_id\":4,\"ekwipunek\":\"inne\"}],\"url_zdjecia\":\"https://s-trojmiasto.pl/zdj/c/n/9/2381/3000x0/2381014.jpg\",\"partia\":{\"partia_id\":11,\"partia\":\"Kardio\"},\"wskazowki\":\"asf\"},\"czasTrwaniaCwiczenia\":\"20\",\"jednoCwiczenie\":[{\"seria\":1,\"czas\":20,\"kilometry\":2.4}]}],\"czasTrwania\":130,\"dataTreningu\":\"2021-02-18\",\"notatka\":\"afasf\",\"typTreningu\":{\"typTreningu\":\"FBW\",\"typtreningu_id\":1}}";
        String response = result.getResponse().getContentAsString();
        response = response.replace("{\"token\":\"", "");
        String token = response.replace("\",\"type\":\"Bearer\",\"id\":1,\"username\":\"krysie\",\"email\":\"ksiedlarski@gmail.com\",\"roles\":[\"ROLE_USER\"]}", "");

        mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/trening/dodajTrening").contentType(MediaType.APPLICATION_JSON)
                .content(body)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    void getTreningi() throws Exception {
        String username = "krysie";
        String password = "qwerty";

        String body = "{\"username\":\"" + username + "\", \"password\":\""
                + password + "\"}";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/auth/signin").contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk()).andReturn();

        String response = result.getResponse().getContentAsString();
        response = response.replace("{\"token\":\"", "");
        String token = response.replace("\",\"type\":\"Bearer\",\"id\":1,\"username\":\"krysie\",\"email\":\"ksiedlarski@gmail.com\",\"roles\":[\"ROLE_USER\"]}", "");

        mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/trening/getTreningi")

                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }
    @Test
    void getTreningiWhereUserDontHaveAnyTraining() throws Exception {
        String username = "test2";
        String password = "qwerty";

        String body = "{\"username\":\"" + username + "\", \"password\":\""
                + password + "\"}";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/auth/signin").contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk()).andReturn();

        String response = result.getResponse().getContentAsString();
        response = response.replace("{\"token\":\"", "");
        String token = response.replace("\",\"type\":\"Bearer\",\"id\":4,\"username\":\"test2\",\"email\":\"test2@gmail.com\",\"roles\":[\"ROLE_USER\"]}", "");

        mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/trening/getTreningi")

                .header("Authorization", "Bearer " + token))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getWybraneCwiczenie() throws Exception {
        String username = "krysie";
        String password = "qwerty";

        String body = "{\"username\":\"" + username + "\", \"password\":\""
                + password + "\"}";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/auth/signin").contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk()).andReturn();

        String response = result.getResponse().getContentAsString();
        response = response.replace("{\"token\":\"", "");
        String token = response.replace("\",\"type\":\"Bearer\",\"id\":1,\"username\":\"krysie\",\"email\":\"ksiedlarski@gmail.com\",\"roles\":[\"ROLE_USER\"]}", "");
 body="{\"cwiczenie_id\":2,\"nazwa_cwiczenia\":\"orbitrek\",\"url_film\":\"https://www.youtube.com/embed/RYlLAzdKXB4\",\"trudnosc\":{\"trudnosc_id\":1,\"trudnosc\":\"początkujący\"},\"ekwipunek\":[{\"ekwipunek_id\":1,\"ekwipunek\":\"maszyna\"}],\"url_zdjecia\":\"https://www.marbo-sport.pl/pol_pl_Orbitrek-elektromagnetyczny-MAXIMUM-EL-8000-Finnlo-28034_10.jpg\",\"partia\":{\"partia_id\":11,\"partia\":\"Kardio\"},\"wskazowki\":\"1.UWAŻAJ NA POSTAWĘ I NIE CHWIEJ SIĘ NA BOKI. 2.ANGAŻUJ RĘCE. 3.PILNUJ, ABY MIĘŚNIE BRZUCHA BYŁY NAPIĘTE 4.ZWRÓĆ UWAGĘ NA RPM, CZYLI ILOŚĆ OBROTÓW NA MINUTĘ, ORAZ TWOJE TĘTNO\"}";
        mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/trening/getWybraneCwiczenie")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    void getTypyTreningu() throws Exception {
        String username = "krysie";
        String password = "qwerty";

        String body = "{\"username\":\"" + username + "\", \"password\":\""
                + password + "\"}";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/auth/signin").contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk()).andReturn();

        String response = result.getResponse().getContentAsString();
        response = response.replace("{\"token\":\"", "");
        String token = response.replace("\",\"type\":\"Bearer\",\"id\":1,\"username\":\"krysie\",\"email\":\"ksiedlarski@gmail.com\",\"roles\":[\"ROLE_USER\"]}", "");

        mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/trening/getPrzykladoweTreningi")

                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    void deletePomiar() throws Exception {
        String username = "krysie";
        String password = "qwerty";

        String body = "{\"username\":\"" + username + "\", \"password\":\""
                + password + "\"}";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/auth/signin").contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk()).andReturn();
body="{\"historiatreningu_id\":120,\"dataTreningu\":\"2021-02-20\",\"czasTrwania\":120,\"notatka\":\"Poprawa techniki w wyciskaniu na klatke\",\"typTreningu\":{\"typtreningu_id\":1,\"typTreningu\":\"FBW\"},\"historiaCwiczen\":[{\"historiacwiczen_id\":122,\"cwiczenie\":{\"cwiczenie_id\":2,\"nazwa_cwiczenia\":\"orbitrek\",\"url_film\":\"https://www.youtube.com/embed/RYlLAzdKXB4\",\"trudnosc\":{\"trudnosc_id\":1,\"trudnosc\":\"początkujący\"},\"ekwipunek\":[{\"ekwipunek_id\":1,\"ekwipunek\":\"maszyna\"}],\"url_zdjecia\":\"https://www.marbo-sport.pl/pol_pl_Orbitrek-elektromagnetyczny-MAXIMUM-EL-8000-Finnlo-28034_10.jpg\",\"partia\":{\"partia_id\":11,\"partia\":\"Kardio\"},\"wskazowki\":\"1.UWAŻAJ NA POSTAWĘ I NIE CHWIEJ SIĘ NA BOKI. 2.ANGAŻUJ RĘCE. 3.PILNUJ, ABY MIĘŚNIE BRZUCHA BYŁY NAPIĘTE 4.ZWRÓĆ UWAGĘ NA RPM, CZYLI ILOŚĆ OBROTÓW NA MINUTĘ, ORAZ TWOJE TĘTNO\"},\"czasTrwaniaCwiczenia\":20,\"jednoCwiczenie\":[{\"jednocwiczenie_id\":4,\"seria\":1,\"powtorzenia\":null,\"czas\":20,\"kilometry\":5,\"ciezar\":null}]},{\"historiacwiczen_id\":124,\"cwiczenie\":{\"cwiczenie_id\":68,\"nazwa_cwiczenia\":\"rotacja wewnętrzna na wyciągu\",\"url_film\":\"https://www.youtube.com/embed/3wfuDa0Jvw8\",\"trudnosc\":{\"trudnosc_id\":1,\"trudnosc\":\"początkujący\"},\"ekwipunek\":[{\"ekwipunek_id\":1,\"ekwipunek\":\"maszyna\"}],\"url_zdjecia\":\"https://www.fabrykasily.pl/upload/gallery/2020/03/id_23186_1585572878_1260x840.jpg\",\"partia\":{\"partia_id\":10,\"partia\":\"Barki\"},\"wskazowki\":\"1) Barki powinny znajdować się w jednej linii zarówno w pozycji wyjściowej, jak i podczas wykonywania ćwiczenia – nie unoś barku ręki wykonującej ćwiczenie.\\n2) Kąt pomiędzy ramieniem a przedramieniem powinien przez cały ruch oscylować w granicy 90 stopni.\\n3) Pilnuj, aby nie odrywać łokcia od tułowia.\\n4) Nie wysuwaj w przód barku ręki ćwiczącej.\\n5) Nie spiesz się podczas wykonywania tego ćwiczenia, zbyt szybkie wykonanie jest błędem technicznym.y.\"},\"czasTrwaniaCwiczenia\":25,\"jednoCwiczenie\":[{\"jednocwiczenie_id\":10,\"seria\":1,\"powtorzenia\":10,\"czas\":null,\"kilometry\":null,\"ciezar\":10}]},{\"historiacwiczen_id\":121,\"cwiczenie\":{\"cwiczenie_id\":41,\"nazwa_cwiczenia\":\"wyciskanie sztangi wąskim chwytem na ławce poziomej\",\"url_film\":\"https://www.youtube.com/embed/b3qYZeDywgI\",\"trudnosc\":{\"trudnosc_id\":1,\"trudnosc\":\"początkujący\"},\"ekwipunek\":[{\"ekwipunek_id\":5,\"ekwipunek\":\"ławka\"},{\"ekwipunek_id\":8,\"ekwipunek\":\"sztanga\"},{\"ekwipunek_id\":7,\"ekwipunek\":\"talerze\"}],\"url_zdjecia\":\"https://i.ytimg.com/vi/2Euup358j6I/maxresdefault.jpg\",\"partia\":{\"partia_id\":1,\"partia\":\"Triceps\"},\"wskazowki\":\"1) Kontroluj ruch podczas trwania ćwiczenia.\\n2) Bądź skoncentrowany.\\n3) Upewnij się, że podczas ruchu prowadzisz łokcie blisko tułowia, tak aby maksymalnie zaangażować mięśnie trójgłowe ramienia.\\n4) Opuszczanie sztangi powinno trwać dwa razy dłużej niż jej wyciskanie.\\n5) Jeżeli wykonujesz to ćwiczenie po raz pierwszy, poproś kogoś o asekurację.\"},\"czasTrwaniaCwiczenia\":20,\"jednoCwiczenie\":[{\"jednocwiczenie_id\":1,\"seria\":1,\"powtorzenia\":10,\"czas\":null,\"kilometry\":null,\"ciezar\":20},{\"jednocwiczenie_id\":2,\"seria\":2,\"powtorzenia\":10,\"czas\":null,\"kilometry\":null,\"ciezar\":25},{\"jednocwiczenie_id\":3,\"seria\":3,\"powtorzenia\":8,\"czas\":null,\"kilometry\":null,\"ciezar\":30}]},{\"historiacwiczen_id\":123,\"cwiczenie\":{\"cwiczenie_id\":105,\"nazwa_cwiczenia\":\"wyciskanie sztangi na ławce skośnej głową w dół\",\"url_film\":\"https://www.youtube.com/embed/B1-Anhkn4xw\",\"trudnosc\":{\"trudnosc_id\":2,\"trudnosc\":\"średniozaawansowany\"},\"ekwipunek\":[{\"ekwipunek_id\":5,\"ekwipunek\":\"ławka\"},{\"ekwipunek_id\":8,\"ekwipunek\":\"sztanga\"},{\"ekwipunek_id\":7,\"ekwipunek\":\"talerze\"}],\"url_zdjecia\":\"https://www.fabrykasily.pl/upload/gallery/2018/07/id_19181_1532953926_1260x840.jpg\",\"partia\":{\"partia_id\":9,\"partia\":\"Klatka\"},\"wskazowki\":\"1) Tempo opuszczania sztangi powinno być dwa razy wolniejsze niż tempo wyciskania.\\n2) Utrzymuj stałe napięcie mięśni.\\n3) Postaraj się nie wyginać nadmiernie nadgarstków.\\n4) Dbaj o staw łokciowy, nie doprowadzaj do przeprostu ramienia pod ciężarem.\\n5) Skup się na prawidłowym oddychaniu.\\n6) Bądź skoncentrowany.\\n7) Jeżeli zamierzasz wyciskać duże ciężary, poproś o pomoc i asekurację.\"},\"czasTrwaniaCwiczenia\":30,\"jednoCwiczenie\":[{\"jednocwiczenie_id\":5,\"seria\":1,\"powtorzenia\":10,\"czas\":null,\"kilometry\":null,\"ciezar\":20},{\"jednocwiczenie_id\":6,\"seria\":2,\"powtorzenia\":10,\"czas\":null,\"kilometry\":null,\"ciezar\":60},{\"jednocwiczenie_id\":7,\"seria\":3,\"powtorzenia\":10,\"czas\":null,\"kilometry\":null,\"ciezar\":80},{\"jednocwiczenie_id\":8,\"seria\":4,\"powtorzenia\":8,\"czas\":null,\"kilometry\":null,\"ciezar\":100},{\"jednocwiczenie_id\":9,\"seria\":5,\"powtorzenia\":5,\"czas\":null,\"kilometry\":null,\"ciezar\":120}]}]}";
        String response = result.getResponse().getContentAsString();
        response = response.replace("{\"token\":\"", "");
        String token = response.replace("\",\"type\":\"Bearer\",\"id\":1,\"username\":\"krysie\",\"email\":\"ksiedlarski@gmail.com\",\"roles\":[\"ROLE_USER\"]}", "");

        mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/trening/deleteTreningi").contentType(MediaType.APPLICATION_JSON)
                .content(body)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    void getPrzykladoweTreningi() throws Exception {
        String username = "krysie";
        String password = "qwerty";

        String body = "{\"username\":\"" + username + "\", \"password\":\""
                + password + "\"}";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/auth/signin").contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk()).andReturn();

        String response = result.getResponse().getContentAsString();
        response = response.replace("{\"token\":\"", "");
        String token = response.replace("\",\"type\":\"Bearer\",\"id\":1,\"username\":\"krysie\",\"email\":\"ksiedlarski@gmail.com\",\"roles\":[\"ROLE_USER\"]}", "");

        mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/trening/getTypyTreningu")

                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    void stworzTrening() throws Exception {
        String username = "krysie";
        String password = "qwerty";

        String body = "{\"username\":\"" + username + "\", \"password\":\""
                + password + "\"}";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/auth/signin").contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk()).andReturn();

        String response = result.getResponse().getContentAsString();
        response = response.replace("{\"token\":\"", "");
        String token = response.replace("\",\"type\":\"Bearer\",\"id\":1,\"username\":\"krysie\",\"email\":\"ksiedlarski@gmail.com\",\"roles\":[\"ROLE_USER\"]}", "");
        body="{\"typTreningu\":{\"typTreningu\":\"FBW\"}}";
        mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/trening/stworzTrening")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    void getRekordy() throws Exception {
        String username = "krysie";
        String password = "qwerty";

        String body = "{\"username\":\"" + username + "\", \"password\":\""
                + password + "\"}";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/auth/signin").contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk()).andReturn();

        String response = result.getResponse().getContentAsString();
        response = response.replace("{\"token\":\"", "");
        String token = response.replace("\",\"type\":\"Bearer\",\"id\":1,\"username\":\"krysie\",\"email\":\"ksiedlarski@gmail.com\",\"roles\":[\"ROLE_USER\"]}", "");

        mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/trening/getRekordy")

                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }
    @Test
    void getRekordyWhereThereIsNoRecords() throws Exception {
        String username = "test2";
        String password = "qwerty";

        String body = "{\"username\":\"" + username + "\", \"password\":\""
                + password + "\"}";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/auth/signin").contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk()).andReturn();

        String response = result.getResponse().getContentAsString();
        response = response.replace("{\"token\":\"", "");
        String token = response.replace("\",\"type\":\"Bearer\",\"id\":4,\"username\":\"test2\",\"email\":\"test2@gmail.com\",\"roles\":[\"ROLE_USER\"]}", "");

        mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/trening/getRekordy")

                .header("Authorization", "Bearer " + token))
                .andExpect(status().isBadRequest());
    }
}