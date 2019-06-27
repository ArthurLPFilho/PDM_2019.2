package filho.arthur.filasagencias;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.util.List;
import filho.arthur.filasagencias.dao.AgenciaDAO;
import filho.arthur.filasagencias.modelo.Agencia;

public class MapaFragment extends SupportMapFragment implements OnMapReadyCallback {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng posicaoDaAgencia = pegaCoordenadaDoEndereco("Avenida Professor Luiz Freire, 500 - Cidade Universitária, Recife, PE");
        if(posicaoDaAgencia != null) {
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(posicaoDaAgencia, 17);
            googleMap.moveCamera(update);
        }
        AgenciaDAO agenciaDAO = new AgenciaDAO(getContext(), 1);
        for(Agencia agencia : agenciaDAO.buscaAgencias()){
            LatLng coordenada = pegaCoordenadaDoEndereco(agencia.getEndereco());
            if(coordenada != null){
                MarkerOptions marcador = new MarkerOptions();
                marcador.position(coordenada);
                marcador.title(agencia.getNome());
                //Forma anterior considerando apenas a ultima avaliacao

                if (agencia.getNota() > 0 && agencia.getNota() <= 4) {
                    marcador.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                }else if (agencia.getNota() > 4 && agencia.getNota() < 8 ){
                    marcador.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                }else if (agencia.getNota() >= 8) {
                    marcador.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                }

                //Considerando agora a media ponderada aritmetica para definir a cor dos pinos
                /*if (agencia.getNota() / agencia.getQtde() > 0 && agencia.getNota() / agencia.getQtde() <= 4) {
                    marcador.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                }else if (agencia.getNota() / agencia.getQtde() > 4 && agencia.getNota() / agencia.getQtde() < 8 ){
                    marcador.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                }else if (agencia.getNota() / agencia.getQtde() >= 8) {
                    marcador.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                }*/
                //Fim da nova forma de calculo

                marcador.snippet(String.valueOf(agencia.getNota()));
                googleMap.addMarker(marcador);
            }
        }
        agenciaDAO.close();
    }
    private LatLng pegaCoordenadaDoEndereco (String endereco){
        try {
            Geocoder geocoder = new Geocoder(getContext());
            List<Address> resultados = geocoder.getFromLocationName(endereco, 1);
            if(!resultados.isEmpty()){
                LatLng posicao = new LatLng(resultados.get(0).getLatitude(), resultados.get(0).getLongitude());
                return posicao;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}