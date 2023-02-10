% first run "readfile" in console or else there will be an error (B undefined)
fclose all

fid = fopen('test2.csv', 'w');
% fid = fopen('test2.txt', 'w');
A=B';
fprintf(fid, '%s,%s\n', 'x', 'y');
fprintf(fid, '%f,%f\n', A);
% fprintf(fid, '%f %f\n', A);

fclose(fid);
