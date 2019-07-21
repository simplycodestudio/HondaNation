package adapters;

/**
 * Created by Jacek on 2018-02-18.
 */
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.*;
import entities.*;
import java.util.*;
import android.view.*;
import com.example.jacek.hondadiagnostic.R;


public class NaprawyListAdapter extends ArrayAdapter<Naprawy> {

    private Context context;
   private List<Naprawy> naprawies;

    public NaprawyListAdapter(@NonNull Context context, List<Naprawy> naprawies) {
        super(context, R.layout.naprawy_layout, naprawies);
        this.context = context;
        this.naprawies = naprawies;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
      LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      View view = layoutInflater.inflate(R.layout.naprawy_layout,parent, false);

      TextView textViewPojazd = (TextView) view.findViewById(R.id.textViewPojazd);
      textViewPojazd.setText(naprawies.get(position).getPojazd());

        TextView textViewPeriod = (TextView) view.findViewById(R.id.textViewPeriod);
        textViewPeriod.setText(naprawies.get(position).getPeriod());

//        TextView textViewPrzebieg = (TextView) view.findViewById(R.id.textViewPrzebieg);
//        textViewPrzebieg.setText(naprawies.get(position).getPrzebieg());
//
//        TextView textViewKwota = (TextView) view.findViewById(R.id.textViewKwota);
//        textViewKwota.setText(naprawies.get(position).getKwota());
//
//        TextView textViewWarsztat = (TextView) view.findViewById(R.id.textViewWarsztat);
//        textViewWarsztat.setText(naprawies.get(position).getWarsztat());
//
//        TextView textViewOpis = (TextView) view.findViewById(R.id.textViewOpis);
//        textViewOpis.setText(naprawies.get(position).getOpis());
      return view;
    }
}
