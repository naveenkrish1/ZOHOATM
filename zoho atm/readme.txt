ZOHO ATM README:
             STEP TO SETUP PROJECT:
			 download zip file submitted
			 extract it
			 then import the file on the following step
			 1.file->import->general->exisitingprojects->path.
			 
			 
			 
			 methods in my projects:
			 
			1.ATM ADDCASH:
			     1.after getting input from the user for no of notes (2000,500,100) respectively
				 2.ATMCASH.txt file is simeltaneously read and write using randomacessfile. 
				 3.noof notes and denomination is taken into seperate variables.
				 4.check whether given note matches to the updating note and use temporaryfilereader.
				 5.stored it in temporaryfilereader
				 6.stored it in separate string and write it as bytes in temporaryfile.
		         7.copied it to the original file.
			
			2.CUSTOMERDETAILS:
			     1.ADD CUSTOMER:
				     1.GET customer account no ,pinno,balance,name;
					 2.check whether account already exist using account no as unique identifier.
					 3.if not exists add the account
					 4.if it exists prompt that account already exists.
				 2.customer details;
				     1.display the file CUSTOMERDETAILS;
		    3.ATM process:
			     1.check account balance:
				     1.get accno and pinno to verify the account
					 2.if verified show the balance.
				 2.withdrawal:
				     1.get accno and pinno to verify it.
					 2.get amount after verification.
					 3.check if amount is greater than 100 ,less than 10000 and multiple of 100.
                     4.first tried to give max 2000 notes and 500 notes so that 100 would be less used
                     5.reduce the amount in account of the user
					 6.reduce denominations in atm textfile.
				3.transfer:
				     1.get accno and pinno to veriy
					 2.get recieveraccno and amount after verification.
					 3.verify recieveraccno.
					 4.reduce the amount in sender.
					 5.increase it in reciever.
				4.atmbalance:
				     1.read the file ATMCASH.
					 2.add the denomination totals.
					 3.display the atmbalance.
					 