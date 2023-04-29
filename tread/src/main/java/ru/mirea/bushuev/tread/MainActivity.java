package ru.mirea.bushuev.tread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;

import ru.mirea.bushuev.tread.databinding.ActivityMainBinding;

//import ru.mirea.bushuev.lesson4.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private	int	counter	=	0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding	=	ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TextView infoTextView = findViewById(R.id.textView);
        Thread mainThread = Thread.currentThread();
        infoTextView.setText("Имя текущего потока: " + mainThread.getName());

        mainThread.setName("МОЙ НОМЕР ГРУППЫ: 06, НОМЕР ПО СПИСКУ: 02, МОЙ ЛЮБИИМЫЙ ФИЛЬМ: Терминатор");
        infoTextView.append("\n Новое имя потока: " + mainThread.getName());
        Log.d(MainActivity.class.getSimpleName(),	"Stack:	"	+	Arrays.toString(mainThread.getStackTrace()));




        binding.buttonMirea.setOnClickListener(new	View.OnClickListener()	{
            @Override
            public	void	onClick(View	v)	{

                new	Thread(new	Runnable()	{
              //      int counter = 1;
                    public	void run()	{
                        int	numberThread = counter++;
                        Log.d("ThreadProject",	String.format("Запущен	поток №%d	студентом группы №%s номер по списку № %d",	numberThread,	"БСБО-06-21",	2));
                        long endTime = System.currentTimeMillis() +	20 * 1000;

                        int twins = Integer.parseInt(String.valueOf(binding.editTextNumberDecimal.getText()));
                        int days = Integer.parseInt(String.valueOf(binding.editTextNumberDecimal2.getText()));
                        if (days != 0){
                            Log.d("ThreadProject",	"Среднее колличество пар:	"	+	twins/days);

                        }

                        while	(System.currentTimeMillis()	< endTime)	{
                            synchronized(this)	{
                                try	{
                                    wait(endTime	- System.currentTimeMillis());
                                    Log.d(MainActivity.class.getSimpleName(),"Endtime:	" +	endTime);
                                }	catch	(Exception	e)	{
                                    throw	new	RuntimeException(e);
                                }
                            }
                            Log.d("ThreadProject","Выполнен поток №" +	numberThread);



                        }
                    }
                }).start();

                int twins = Integer.parseInt(String.valueOf(binding.editTextNumberDecimal.getText()));
                int days = Integer.parseInt(String.valueOf(binding.editTextNumberDecimal2.getText()));
                if (days != 0){
                    Log.d("ThreadProject","Среднее колличество пар:"	+	twins/days);
                    binding.textView.setText(String.valueOf(twins/days));
                }

            }
        });
    }

}