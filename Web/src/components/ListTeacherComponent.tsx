import { IconPencil, IconTrash } from '@tabler/icons-react';
import {ActionIcon, Group, Loader, Table, Text, Notification, Box} from '@mantine/core';
import {useMutation, useQuery} from "@tanstack/react-query";
import {fetchTeachers} from "../api/service.ts";
import {useNavigate} from "react-router-dom";
import {useEffect} from "react";
import {axiosConfig} from "../../axios.ts";
import {queryClient} from "../api/query-client.ts";


export default function ListTeacherComponent() {
    const {status, data, error } = useQuery(["teachers"], fetchTeachers);
    const navigate = useNavigate();
    useEffect(() => {
        const userId = localStorage.getItem('userRole');
        if (userId === "TEACHER") {
            navigate('/active-tests');
        }
    }, [navigate]);
    const deleteTeacher = async (id: number) => {
        const params = {
            id: id
        };
        return (await axiosConfig.delete('/api/worker/by-id', {params})).data;
    }
    const {mutate} = useMutation(deleteTeacher, {
        onSuccess: () => {
            queryClient.invalidateQueries(["teachers"]);
        },
    });

    const handleDelete = (id:number) => {
        mutate(id);
    };
    if(status === "loading") {
        return (
        <Box style={{display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100%'}}>
            <Loader size="lg"/>
        </Box>
        )
    } else if (status === "error") {
        return (
        <Notification color="red" title="Error loading">
            {error.message || 'An unknown error occurred.'}
        </Notification>
        )
    }
    const teachers = data.data.filter((teacher) => teacher.workerRole === "TEACHER")
    const rows = teachers.map((item) => (
        <Table.Tr key={item.firstName + " " + item.lastName}>
            <Table.Td>
                <Group gap="sm">
                    <Text fz="sm" fw={500}>
                        {item.firstName + " " + item.lastName}
                    </Text>
                </Group>
            </Table.Td>
            <Table.Td>
                <Group gap={0} justify="flex-end">
                    <ActionIcon variant="subtle" color="gray">
                        <IconPencil size={16} stroke={1.5} />
                    </ActionIcon>
                    <ActionIcon onClick={() => handleDelete(item.id)} variant="subtle" color="red">
                        <IconTrash size={16} stroke={1.5} />
                    </ActionIcon>
                </Group>
            </Table.Td>
        </Table.Tr>
    ));

    return (
        <Table.ScrollContainer minWidth={800}>
            <Table verticalSpacing="sm">
                <Table.Tbody>{rows.length !== 0 ? rows : <Group justify={"center"}>Нет Преподавателей</Group>}</Table.Tbody>
            </Table>
        </Table.ScrollContainer>
    );
}