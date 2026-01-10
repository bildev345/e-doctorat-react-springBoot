package org.example.doctoratrestapi.laboratoire;

import org.example.doctoratrestapi.dtos.labo.LaboratoireDTO;
import org.example.doctoratrestapi.mappers.laboratoire.LaboratoireMapper;
import org.example.doctoratrestapi.models.InscriptionModel;
import org.example.doctoratrestapi.inscription.InscriptionRepository;
import org.example.doctoratrestapi.models.LaboratoireModel;
import org.example.doctoratrestapi.models.ProfesseurModel;
import org.example.doctoratrestapi.sujet.SujetModel;
import org.example.doctoratrestapi.sujet.SujetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class LaboratoireService {

    @Autowired private LaboratoireRepository laboratoireRepository;

    @Autowired private SujetRepository sujetRepository;

    @Autowired private InscriptionRepository inscriptionRepository;

    @Autowired private LaboratoireMapper mapper;

    // GET /laboratoires/{id} → infos labo simple
    public LaboratoireDTO getLaboratoire(Long id) {
        LaboratoireModel labo = laboratoireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Laboratoire non trouvé"));
        return mapper.toDTO(labo);
    }

    //GET /laboratoires/{id}/sujets → sujets du labo (via formation)
    public List<SujetModel> getSujetsByLaboratoire(Long labId) {
        return sujetRepository.getSujetsByLaboId(labId);    }

    // 3. GET /laboratoires/{id}/candidats → candidats du labo
    public List<InscriptionModel> getCandidatsByLaboratoire(Long labId) {
        LaboratoireModel labo = laboratoireRepository.findById(labId)
                .orElseThrow(() -> new RuntimeException("Labo non trouvé"));

        ProfesseurModel directeur = labo.getDirecteur();
        if (directeur == null) return new ArrayList<>();

        return inscriptionRepository.getCandidatsByDirecteurId(directeur.getId());
    }

    // 4. POST /laboratoires/{id}/sujets/upload
    public String uploadSujetsCSV(Long labId, MultipartFile file) {
        try {

            //Ouvre le fichier CSV et lis-le ligne par ligne (BufferedReader)
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream())); //InputStreamReader :Transforme le flux binaire en flux texte
            List<SujetModel> sujets = new ArrayList<>();

            String line; //Cette variable contiendra chaque ligne du fichier CSV
            reader.readLine(); //Cette ligne lit la première ligne du CSV mais ne l’utilise pas parcque La première ligne est un en-tête, pas une donnée réelle

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(","); //découpe la ligne CSV, en utilisant la virgule
                if (data.length >= 2) {
                    SujetModel sujet = new SujetModel();
                    sujet.setTitre(data[0].trim().replace("\"", ""));
                    sujet.setDescription(data[1].trim().replace("\"", ""));
                    sujets.add(sujet);
                }
            }

            sujetRepository.saveAll(sujets);
            return "Succès : " + sujets.size() + " sujets créés";

        } catch (Exception e) {
            return "Erreur : " + e.getMessage();
        }
    }

    // 5. GET /laboratoires/{id}/pv-global → CRITIQUE prof
    public byte[] generatePvGlobal(Long labId) {
        List<InscriptionModel> inscriptions = getCandidatsByLaboratoire(labId);

        StringBuilder csv = new StringBuilder(); //Adapté pour concaténer beaucoup de lignes que String
        csv.append("Sujet,Candidat,DateDossier,Valider\n"); //Ajout de la ligne d’en-tête

        for (InscriptionModel i : inscriptions) {
            csv.append(String.format("\"%s\",\"%s\",\"%s\",%.1f,%.1f,\"%s\"\n",
                    i.getSujet().getTitre(),
                    i.getCandidat().getNomCandidatArabe(),
                    i.getDateDeposerDossier(),
                    i.getValider()
            ));
        }

        return csv.toString().getBytes();
    }

}
